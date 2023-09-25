# Rename column name
# registed_at => registered_at
ALTER Table delivery.user 
CHANGE COLUMN registed_at registered_at DATETIME;