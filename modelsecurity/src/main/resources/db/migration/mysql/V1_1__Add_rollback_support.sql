-- Rollback support for MySQL
-- This script demonstrates how to handle rollbacks
-- Note: Flyway doesn't support automatic rollbacks, but you can create undo scripts

-- Example: If you need to rollback V1__Initial_schema.sql, you would create a script like:
-- DROP TABLE IF EXISTS rol_form_permit;
-- DROP TABLE IF EXISTS rol_user;
-- DROP TABLE IF EXISTS form_module;
-- DROP TABLE IF EXISTS module;
-- DROP TABLE IF EXISTS form;
-- DROP TABLE IF EXISTS permission;
-- DROP TABLE IF EXISTS rol;
-- DROP TABLE IF EXISTS user;
-- DROP TABLE IF EXISTS person;

-- For error handling in migrations, Flyway will automatically rollback transactions on failure
-- when using transactional databases like MySQL

-- This is a placeholder file for rollback documentation
-- In practice, you would create separate undo migration files for complex rollbacks