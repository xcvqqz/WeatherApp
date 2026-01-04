CREATE TABLE Locations (
    id INT PRIMARY KEY,
    name VARCHAR(30) NOT NULL ,
    user_id INT NOT NULL,
    latitude DECIMAL NOT NULL,
    longitude DECIMAL NOT NULL
);