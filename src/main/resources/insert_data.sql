
--DELETE FROM PROFILE;--
INSERT INTO profile(profile, description) VALUES ('TEST', 'For Tests');
INSERT INTO profile(profile, description) VALUES ('ADMIN', 'Platform Administrator');
INSERT INTO profile(profile, description) VALUES ('NEW_USER', '');
INSERT INTO profile(profile, description) VALUES ('OCCASIONAL_USER', '');
INSERT INTO profile(profile, description) VALUES ('REGULAR_USER', 'Regularly updates their profile with new skills, experiences, and reviews');
INSERT INTO profile(profile, description) VALUES ('SELF_EMPLOYED', '');
INSERT INTO profile(profile, description) VALUES ('SERVICE_FINDER', '');
-------------------------------------------
---new tables-----
-------------------------------------------
INSERT INTO Category (title, picture) VALUES
                                          ('CoursParticuliers', 'assets/coursParticuliers.jpg'),
                                          ('Aide à domicile', 'assets/aideDomicile.jpg'),
                                          ('Informatique', 'assets/informatique.jpg'),
                                          ('Santé', 'assets/sante.jpg'),
                                          ('Livraison', 'assets/livraison.jpg'),
                                          ('Sports', 'assets/sport.jpg'),
                                          ('Recrutement', 'assets/recrutement.jpg'),
                                          ('Evènementiel', 'assets/evenementiel.jpg'),
                                          ('Construction', 'assets/construction.jpg'),
                                          ('Beaute', 'assets/beaute.jpg'),
                                          ('Nettoyage', 'assets/nettoyage.jpg'),
                                          ('AideDomicile', 'assets/aideDomicile.jpg'),
                                          ('LogistiqueTransport', 'assets/logistique.jpg');
INSERT INTO Pays (codePays, nomPays) VALUES
                                         ('GIN', 'Guinee'),
                                         ('TGO', 'Togo'),
                                         ('CIV', 'Côte d''Ivoire');
INSERT INTO Communes (codePays, nomCommune) VALUES
    ('GIN', 'Kaloum');