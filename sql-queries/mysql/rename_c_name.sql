-- Active: 1697006872503@@121.254.195.229@3306
# Rename column name
# registed_at => registered_at
ALTER Table delivery.user 
CHANGE COLUMN registed_at registered_at DATETIME;

# thumnail_url => thumbnail_url Column Name Change
ALTER TABLE store 
	CHANGE thumbnail_url thumbnail_url VARCHAR(500) Not Null;