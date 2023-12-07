/*-------------------------------------------------------------------------------------------*/
/*                                                                                           */
/*                       CREATION ET INSERTION DES ROLES DU JEU                              */
/*                                                                                           */
/*-------------------------------------------------------------------------------------------*/

DROP TABLE IF EXISTS CanRole;

CREATE TABLE CanRole 
(
	nomRole    VARCHAR (20) PRIMARY KEY                                         , -- Nom du role
	resistance FLOAT        DEFAULT 1.0 CHECK (resistance > 0)                  , -- Resistance du personnage au dégat
	rassasier  FLOAT        DEFAULT 1.0 CHECK (rassasier  > 0)                  , -- Baisse de la faim quand ca mange
	repos      FLOAT        DEFAULT 1.0 CHECK (repos      > 0)                  , -- Baisse de la fatigue quand on dort
	fatigue    FLOAT        DEFAULT 1.0 CHECK (fatigue    > 0)                  , -- Augmentation de la fatigue lors de mouvement
	ressource  FLOAT        DEFAULT 1.0 CHECK (ressource  > 0)                  , -- Quantite de ressource trouver en 
	descRole   VARCHAR()    DEFAULT 'N/A'                                       , -- Description du rôle narrateur
	pouvRole   VARCHAR()    DEFAULT 'N/A'                                       , -- Description du pouvoir du role
	team       VARCHAR()    DEFAULT 'CLAIRE' CHECK (team IN ('CLAIRE', 'AUTRE'))  -- Equipe du role
);


/*            */
/* CANNIBALES */
/*            */

INSERT INTO CanRole (nomRole, rassasier, descRole, pouvRole, team) 
VALUES      (
	         'Cannibale', 0.5, 
             'La nourriture trouvée sur l''île ne vous suffisait pas, et vous n''avez pas eu le choix de simplement sacrifier l''un de vos équipiers pour vous nourrir. Maintenant que le groupe a compris le danger qui rôde, il est important d''être plus méfiant. Cependant, votre faim persiste, et il faudra toujours la satisfaire.',
			 'Choissisez durant la nuit une personne à manger. Vous pouvez ne pas tuer quelqu''un, mais s''en empécher ne rassasiera pas votre faim',
			 'AUTRE'
			);


/*           */
/* NAUFRAGES */
/*           */
INSERT INTO CanRole (nomRole, descRole) 
VALUES      (
	         'Naufrage', 
			 'Simple naufragés, il est important de vous assurer de votre survie et aussi de celle de votre équipe. Mais depuis l''accident, vous vous êtes rendu compte que le danger ne vient pas seulement de l''île mais aussi de votre équipe...'
			);


/*              */
/* INSOMNIAQUES */
/*              */

INSERT INTO CanRole (nomRole, repos, fatigue, descRole, pouvRole) 
VALUES      (
	         'Insomniaque', 0.75, 1.25, 
             'Depuis l''accident, vous n''arrivez plus à fermer l''œil de la nuit. Inquiet.te pour votre vie, vous passez vos nuits à vous réveiller pour surveiller votre abri et ses alentours par peur de vous faire attaquer.',
			 'Vous vous fatiguez plus vite dans la journée et vous vous reposez moins la nuit. Cependant, vous survivrez à la première attaque des cannibales, et avec un peu de chance, vous pourrez aussi découvrir l''identité de votre agresseur.'
			);


/*      */
/* FOUS */
/*      */

INSERT INTO CanRole (nomRole, ressource, descRole, pouvRole, teame) 
VALUES      (
	         'Fou', 0.5,
             'Retrouver le cadavre de votre ami à moitié mangé vous a beaucoup touché. Vous êtes sûr.e que vous serez le.la prochain.e, et que l''entièreté de votre équipe vous veut du mal. Vous avez peur d''aller dans la forêt, de peur de vous faire attaquer par l''un de ces fous.',
			 'Vous trouverez moins de ressources en allant dans la forêt. Si vous êtes voté.e par l''équipe pour être poussé.e de la falaise, vous emporterez au hasard un membre de l''équipe avec vous.',
			 'AUTRE'
			);


/*         */
/* DOCTEUR */
/*         */

INSERT INTO CanRole (nomRole, resistance, descRole, pouvRole) 
VALUES      (
	         'Docteur', 0.75,
             'Personne médecin.ne du navire désormais échoué, vous avez la possibilité de choisir de soigner l''un.e de vos camarades pendant la journée. Cela vous demandera cependant beaucoup d''énergie et vous expose au risque de tomber malade vous aussi, ce qui peut être dangereux vu votre faible système immunitaire.',
			 'Vous avez la possibilité de redonner de la vie à une personne de votre choix pendant la journée, ou bien de tenter de soigner une personne d''une maladie en prenant le risque de tomber malade vous-même. Vous prenez davantage de dégâts en raison de votre faiblesse.'
            );


/*          */
/* CHASSEUR */
/*          */

INSERT INTO CanRole (nomRole, fatigue, ressource, descRole, pouvRole, team) 
VALUES      (
	         'Chasseur', 1.5, 1.5, 
             'Ancien.ne chasseur.se, vous avez plus de facilité à retrouver des ressources. Cependant, cela vous demande plus d''énergie.',
			 'Vous trouvez plus de ressources mais perder plus d''énergie.',
			 'AUTRE'
            );


/*            */
/* DECTECTIVE */
/*            */

INSERT INTO CanRole (nomRole, descRole, pouvRole) 
VALUES      (
	         'Dectective',
             'Détective, vous ne pouvez ignorer l''accident qui vient de se produire. Vous comptez bien trouver le.la ou les responsables de cette histoire. Mais être détective n''est pas de tout repos, et vous vous voyez souvent raccourcir vos nuits pour enquêter.',
			 'Chaque nuit, vous pouvez choisir deux joueurs pour voir s''ils sont de la même équipe. Cependant, si vous enquêtez, vous ne vous reposerez donc pas pendant la nuit.'
            );





/*-------------------------------------------------------------------------------------------*/
/*                                                                                           */
/*                               CREATION DES JOUEURS DU JEU                                 */
/*                                                                                           */
/*-------------------------------------------------------------------------------------------*/

DROP TABLE IF EXISTS CanJoueur;

CREATE TABLE CanJoueur 
(
	nomJoueur      VARCHAR() PRIMARY KEY                                 ,
	roleJoueur     VARCHAR() REFERENCES CanRole(nomRole)                 ,
	enVie          BOOLEAN   DEFAULT    TRUE                             ,
	vieJoueur      INTEGER   CHECK      (vieJoueur     BETWEEN 0 AND 100),
	faimJoueur     INTEGER   CHECK      (faimJoueur    BETWEEN 0 AND 100),
	fatigueJoueur  INTEGER   CHECK      (fatigueJoueur BETWEEN 0 AND 100),
	ordrePassage   INTEGER   CHECK      (ordrePassage > 0)     
);





/*-------------------------------------------------------------------------------------------*/
/*                                                                                           */
/*                     CREATION ET INSERTION DES NOURRITURES DU JEU                          */
/*                                                                                           */
/*-------------------------------------------------------------------------------------------*/

DROP TABLE IF EXISTS CanNourriture;

CREATE TABLE CanNourriture 
(
	nomNourriture   VARCHAR() PRIMARY KEY                                        ,
	descNourriture  VARCHAR() PRIMARY KEY                                        ,
	vieEnlever      INTEGER   CHECK      (vieEnlever BETWEEN 0 AND 100) DEFAULT 0,
	rassasier       INTEGER   CHECK      (rassasiera BETWEEN 0 AND 100)          ,
	rarete          VARCHAR() CHECK      (rarete     IN ('COMMUN', 'RARE'))     
); 

INSERT INTO CanNourriture (nomNourriture, descNourriture, rassasier, rarete)
VALUES      ('BAIES NOIRES', 'De jolies baies rouges qui dégage une odeur sucrée. L''odeur correspond bien à leur gout.'                    , 10, 'COMMUN'),
            ('PISSENLIT'   , 'Il n''ya pas de salade à décoré, mais qui sommes nous pour refuser un peu de nourriture.'                     ,  8, 'COMMUN'),
            ('TREFLES'     , 'La chance est avec nous... ou pas. Dans tous les cas, cette plante peut servir d''en cas'                     ,  5, 'COMMUN'),
            ('FRUIT OVAL'  , 'Un étrange fruit avec une forme oval. Légérement musqué, il servira de bon repas.'                            , 15, 'COMMUN'),
            ('OEUFS'       , 'Vous avez eu la chance de tomber sur quelques oeufs en grimpant sur un arbre.'                                , 20, 'RARE'  ),
            ('RONGEUR'     , 'Vous avez réussi à tué un petit rongeur. Un peu de viande ne vous ferra pas de mal.'                          , 30, 'RARE'  ),
            ('POISSON'     , 'La pêche à enfin marché ! Quel magnifique poisson vous avez la. Il ferra un magnifique repas'                 , 40, 'RARE'  ),
            ('GROS POISSON', 'Vous n''avez jamais vue un poisson de cette taille ! Il sera suffisant pour vous rassacier sur quelque jour !', 80, 'RARE'  );
	

INSERT INTO CanNourriture (nomNourriture, descNourriture, vieEnlever, rassasier, rarete)
VALUES      ('BAIES SUCRES'      , 'De jolies baies rouges qui ressemble à des baies de houx. Vous en avez déja mangé jeunes, quel danger peut il y avoir a en remanger ?'        ,10 , 10, 'COMMUN'),
            ('FEUILLES INCONNUES', 'Vous êtes persuadés d''avoir déja vue ces feuilles quelques parts, mais vous n''arrivez pas à vous souvenir dans quel livres elles étaient...', 3 ,  5, 'COMMUN'),
            ('FRUIT POURRIS'     , 'Vous avez déja vue ce fruit en meuilleur état... Mais bon, si on ferme un peu les yeux, ils restent commestible'                              ,15 ,  5, 'COMMUN'),
            ('CARCASSE D''OISEAU', 'Vous ne savez ce qui a pu tuer cette oiseau. Ce que vous savez cependant, c''est qu''il reste un peu de viande dessus...'                     ,30 , 20, 'RARE'  );













