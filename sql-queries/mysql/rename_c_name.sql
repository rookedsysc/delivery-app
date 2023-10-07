# Rename column name
# registed_at => registered_at
ALTER Table delivery.user 
CHANGE COLUMN registed_at registered_at DATETIME;

# thumnail_url => thumbnail_url Column Name Change
ALTER TABLE store 
	CHANGE thumbnail_url thumbnail_url VARCHAR(500) Not Null;