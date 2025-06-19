-- Inserindo espécies
INSERT INTO public.especie (esp_nome) VALUES 
('Cachorro'),
('Gato'),
('Pássaro'),
('Coelho'),
('Hamster'),
('Peixe'),
('Réptil'),
('Cavalo'),
('Porquinho-da-índia'),
('Tartaruga');

-- Inserindo raças para Cachorro
INSERT INTO public.raca (rac_nome, esp_id) VALUES
('Poodle', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Cachorro')),
('Labrador', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Cachorro')),
('Bulldog', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Cachorro')),
('Golden Retriever', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Cachorro')),
('Vira-lata', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Cachorro')),
('Chihuahua', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Cachorro')),
('Husky Siberiano', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Cachorro')),
('Pastor Alemão', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Cachorro'));

-- Inserindo raças para Gato
INSERT INTO public.raca (rac_nome, esp_id) VALUES
('Persa', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Gato')),
('Siamês', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Gato')),
('Maine Coon', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Gato')),
('Sphynx', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Gato')),
('Vira-lata', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Gato')),
('Ragdoll', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Gato')),
('Bengal', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Gato'));

-- Inserindo raças para Pássaro
INSERT INTO public.raca (rac_nome, esp_id) VALUES
('Canário', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Pássaro')),
('Calopsita', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Pássaro')),
('Papagaio', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Pássaro')),
('Periquito', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Pássaro')),
('Agapornis', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Pássaro')),
('Maritaca', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Pássaro'));

-- Inserindo raças para Coelho
INSERT INTO public.raca (rac_nome, esp_id) VALUES
('Mini Lion', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Coelho')),
('Holland Lop', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Coelho')),
('Netherland Dwarf', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Coelho')),
('Angorá', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Coelho')),
('Mini Rex', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Coelho'));

-- Inserindo raças para Hamster
INSERT INTO public.raca (rac_nome, esp_id) VALUES
('Sírio', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Hamster')),
('Anão Russo', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Hamster')),
('Anão Roborovski', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Hamster')),
('Chinês', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Hamster')),
('Campbell', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Hamster'));

-- Inserindo raças para Peixe
INSERT INTO public.raca (rac_nome, esp_id) VALUES
('Betta', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Peixe')),
('Neon', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Peixe')),
('Guppy', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Peixe')),
('Molinésia', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Peixe')),
('Platy', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Peixe')),
('Espada', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Peixe'));

-- Inserindo raças para Réptil
INSERT INTO public.raca (rac_nome, esp_id) VALUES
('Iguana', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Réptil')),
('Jiboia', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Réptil')),
('Corn Snake', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Réptil')),
('Gecko', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Réptil')),
('Tartaruga Tigre-d’água', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Réptil'));

-- Inserindo raças para Cavalo
INSERT INTO public.raca (rac_nome, esp_id) VALUES
('Mangalarga', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Cavalo')),
('Quarto de Milha', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Cavalo')),
('Puro Sangue Árabe', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Cavalo')),
('Frísio', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Cavalo'));

-- Inserindo raças para Porquinho-da-índia
INSERT INTO public.raca (rac_nome, esp_id) VALUES
('Abissínio', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Porquinho-da-índia')),
('Peruano', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Porquinho-da-índia')),
('Sheltie', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Porquinho-da-índia')),
('Texel', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Porquinho-da-índia'));

-- Inserindo raças para Tartaruga
INSERT INTO public.raca (rac_nome, esp_id) VALUES
('Tartaruga Tigre-d’água', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Tartaruga')),
('Tartaruga de Pente', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Tartaruga')),
('Tartaruga-de-couro', (SELECT esp_id FROM public.especie WHERE esp_nome = 'Tartaruga'));