CREATE TABLE Locations (
    location_id INT PRIMARY KEY,
    name VARCHAR(30) NOT NULL ,
    user_id INT NOT NULL,
    latitude DECIMAL NOT NULL,
    longitude DECIMAL NOT NULL,
    FOREIGN KEY (location_id) REFERENCES users(user_id) ON DELETE CASCADE
);