-- Add password column to passengers table for authentication
ALTER TABLE passengers ADD COLUMN password VARCHAR(255) NOT NULL;