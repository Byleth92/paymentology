create table transaction_source 
(id int auto_increment, 
profile_name varchar, 
transaction_date timestamp, 
transaction_amount varchar, 
transaction_narrative varchar, 
transaction_description varchar, 
transaction_type varchar, 
wallet_reference varchar);

create table probable_matches 
(id int auto_increment, 
probable_match_id varchar, 
transaction_date timestamp, 
transaction_amount varchar, 
transaction_narrative varchar, 
transaction_description varchar, 
transaction_type varchar, 
wallet_reference varchar);