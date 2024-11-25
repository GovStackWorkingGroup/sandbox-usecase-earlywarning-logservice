CREATE TABLE log (
                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                     text VARCHAR(555),
                     processed BOOLEAN DEFAULT FALSE,
                     broadcast TEXT NOT NULL,
                     timestamp TIMESTAMP DEFAULT NOW()
);
