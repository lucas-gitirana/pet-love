-- Desabilita as restrições temporariamente
DO $$
DECLARE
    r RECORD;
BEGIN
    -- Desabilita constraints de chave estrangeira
    FOR r IN (SELECT tablename FROM pg_tables WHERE schemaname = 'public')
    LOOP
        EXECUTE 'ALTER TABLE public.' || quote_ident(r.tablename) || ' DISABLE TRIGGER ALL;';
    END LOOP;
END $$;

-- Truncar todas as tabelas (com reinício dos IDs se desejar)
DO $$
DECLARE
    r RECORD;
BEGIN
    FOR r IN (SELECT tablename FROM pg_tables WHERE schemaname = 'public')
    LOOP
        EXECUTE 'TRUNCATE TABLE public.' || quote_ident(r.tablename) || ' RESTART IDENTITY CASCADE;';
    END LOOP;
END $$;

-- Habilita novamente as restrições
DO $$
DECLARE
    r RECORD;
BEGIN
    -- Habilita constraints de chave estrangeira
    FOR r IN (SELECT tablename FROM pg_tables WHERE schemaname = 'public')
    LOOP
        EXECUTE 'ALTER TABLE public.' || quote_ident(r.tablename) || ' ENABLE TRIGGER ALL;';
    END LOOP;
END $$;
