# Bash script to add a listener port
# Created by Niraj Mahajan for Start App

# usage -> run the bash script as root
# usage -> enter an organization name and the port to be alloted
# The code will automatically check for repetitions in db name and the port
# It also restarts apache and adds the port exception in firewall


# function declarations
#######################################

isNumber () {
	case $1 in
    	''|*[!0-9]*) return 1 ;;
    	*) return 0 ;;
	esac
}

isValidNumber () {
	if  isNumber $1  ; then
		if [ "$1" -le 99999 ]; then
			if [ "$1" -ge 1025 ]; then
				return 0
			else
				return 1
			fi
		else 
			return 1
		fi
	else
		return 1
	fi
}

#######################################


#!/bin/bash

if [ "$(id -u)" != "0" ]; then
	/bin/echo "Sorry, you are not root."
	exit 1
fi

# check if template directory is present
if [ ! -d "/var/www/template" ]; then
	echo "\"/var/www/template\" not found : "
	exit 1
fi
# check if start.sql is present
if [ ! -f "/var/www/template/start.sql" ]; then
	echo "\"/var/www/template/start.sql\" not found : "
	exit 1
fi
# check if modify.sql is present
if [ ! -d "/var/www/template" ]; then
	echo "\"/var/www/template/modify.sql\" not found : "
	exit 1
fi

/bin/echo "Usage : Enter the Organization Name, and the port to be alloted"

# take input
read -p "Name: " Name
DBName="${Name}db"

# verify that the name is unique
while mysql -u root -p'12345678' -e 'use '"$DBName"'' 2> /dev/null; do 
	/bin/echo "The name "$DBName" has been taken"
	read -p  "Choose another name: " Name
	DBName="${Name}db"
done

DBUsername="${Name}Username"
DBPassword="${Name}12345678"
DirectoryName="$Name"
ErrorLogFile="error${Name}.log"
AccessLogFile="access${Name}.log"


# Take input for port
cd /etc/apache2/
read -p "Port to be alloted: " Port

while  ! isValidNumber $Port ; do
	read -p "Input a valid Number  (1025 <= n < 100000) : " Port	
done

# make sure the input port is unique and valid
while  grep -wq "$Port" ports.conf ; do
	read -p "Port taken. Choose another: " Port

	while  ! isValidNumber $Port ; do
		read -p "Input a valid Number  (1025 <= n < 100000)  : " Port	
	done

done


# add the port in port.conf
/bin/touch temp
printf "Listen $Port\n" >> temp
/bin/cat ports.conf >> temp
mv temp ports.conf

# add the port in 000-defaultconfig
cd sites-enabled/

printf "

<VirtualHost *:$Port>

        ServerAdmin webmaster@localhost
        DocumentRoot /var/www/$DirectoryName

            <Directory />
                Options FollowSymLinks
                AllowOverride None
            </Directory>
            <Directory /var/www/$DirectoryName>
                Options Indexes FollowSymLinks MultiViews
                AllowOverride All
                Order allow,deny
            allow from all
            </Directory>

        ErrorLog \${APACHE_LOG_DIR}/$ErrorLogFile
        CustomLog \${APACHE_LOG_DIR}/$AccessLogFile combined

</VirtualHost>\n\n" >> 000-default.conf


# create database and user
mysql -u root -p'12345678' -e 'create database '"$DBName"';'
mysql -u root -p'12345678' -e "create user '${DBUsername}'@'localhost' identified by '${DBPassword}';"
mysql -u root -p'12345678' -e "Grant All Privileges on ${DBName}.* to '${DBUsername}'@'localhost';"
mysql -u root -p'12345678' -e "FLUSH Privileges;"
cd /var/www/template
mysql -u root -p'12345678' "$DBName" < start.sql
mysql -u root -p'12345678' "$DBName" < modify.sql


# copy directories
cd /var/www/
/bin/cp -r template "$DirectoryName"

# change db.php in outer config folder
cd /var/www/"$DirectoryName"/config
/bin/rm db.php
/bin/touch db.php

printf "

<?php

return [
    'class' => 'yii\db\Connection',
    'dsn' => 'mysql:host=localhost;dbname=$DBName',
    'username' => '$DBUsername',
    'password' => '$DBPassword',
    'charset' => 'utf8',
];\n\n
" >> db.php


# change db.php in inner config file
cd /var/www/"$DirectoryName"/start_cms/app/config
/bin/rm db.php
/bin/touch db.php
printf "

<?php

defined('_VALID_PHP') or die('No direct script access.');

define('DB_HOST', 'localhost');
define('DB_USER', '$DBUsername');
define('DB_PASSWORD', '$DBPassword');
define('DB_NAME', '$DBName');
define('DB_CHARSET', 'utf8mb4');
define('DB_AES_KEY', 't4-h8_hv5^f');

?>\n\n
" >> db.php

#change dirs.php in inner config file
/bin/rm dirs.php
/bin/touch dirs.php
printf "
<?php

defined('_VALID_PHP') or die('No direct script access.');

define('SITE', '//35.200.251.93:$Port/');
define('CMS_DIR', 'start_cms/');
define('UPLOADS_DIR', '../uploads/');
define('APP_DIR', 'app/');
define('LANG_DIR', APP_DIR.'langs/');
define('MODEL_DIR', APP_DIR.'models/');
define('CONTROLLER_DIR', APP_DIR.'controllers/');
define('VIEW_DIR', APP_DIR.'views/');
define('TPL_DIR', APP_DIR.'views/actions/');
define('WIDGET_DIR', APP_DIR.'views/widgets/');
//define('PLUGINS_DIR', 'vendor/');
define('VENDOR_DIR', 'vendor/');
define('CORE_DIR', 'vendor/tb/start_cms/');
define('HELPER_DIR', CORE_DIR.'helpers/');
define('WEB_DIR', 'web/');
define('CSS_DIR', WEB_DIR.'css/');
define('JS_DIR', WEB_DIR.'js/');
define('IMAGE_DIR', WEB_DIR.'images/');

define('CONSENT_FO/bin/RM_FILE_ENGLISH', '../consent_fo/bin/rm_english.txt');
define('CONSENT_FO/bin/RM_FILE_HINDI', '../consent_fo/bin/rm_hindi.txt');

?>
" >> dirs.php

ufw allow $Port
service apache2 restart

/bin/echo "Success!"