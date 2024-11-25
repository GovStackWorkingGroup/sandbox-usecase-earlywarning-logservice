CREATE TABLE log (
                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                     sender VARCHAR(555),
                     receiver VARCHAR(555),
                     content VARCHAR(555),
                     processed BOOLEAN DEFAULT FALSE,
                     broadcast TEXT NOT NULL,
                     timestamp TIMESTAMP DEFAULT NOW()
);
