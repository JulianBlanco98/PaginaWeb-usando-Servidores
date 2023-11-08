
use tw;

CREATE USER 'tw'@'localhost' IDENTIFIED BY 'tw2223';
GRANT ALL PRIVILEGES ON tw.* TO 'tw'@'localhost';
FLUSH PRIVILEGES;

