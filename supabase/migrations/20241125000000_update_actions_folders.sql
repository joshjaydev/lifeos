-- Migration: Update actions and folders tables
-- Date: 2024-11-25
-- Description: Remove description from actions table and add color to folders table

-- Add color column to folders table
ALTER TABLE folders ADD COLUMN IF NOT EXISTS color BIGINT;

-- Remove description column from actions table
ALTER TABLE actions DROP COLUMN IF EXISTS description;

-- Update any existing folders to have a default color (LifeOS Green - 0xFF32CD33 as ARGB Int)
UPDATE folders SET color = -11116749 WHERE color IS NULL;
