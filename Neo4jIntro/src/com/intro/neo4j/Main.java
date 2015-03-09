package com.intro.neo4j;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import com.intro.neo4j.nodeLabels.NodeLabels;
import com.intro.neo4j.typeRelation.TypeRelation;

public class Main {

	public static void main(String[] args) {
		
		/* Création d'une nouvelle BDD Orientée Graphe */

		GraphDatabaseFactory dbFactory = new GraphDatabaseFactory();
		GraphDatabaseService db = dbFactory.newEmbeddedDatabase("C:\\Zakaria\\NeoTests\\IntroNeo4j"); // Vous pouvez définir votre path vers un dossier vide
		
		/* Début des transactions (Dans notre exemple on va se concentrer sur l'écriture dans la BDDG) */
		
		try (Transaction tx = db.beginTx()) {
			
			Node bernard = db.createNode(NodeLabels.AUTEUR); // Création d'un nouveau noeud
			bernard.setProperty("NomLong", "DOUSSET Bernard"); // Ajout d'une propriété à ce noeud, vous pouvez en ajouter autant que vous voulez 
															   // Toujours en respectant le couple (nom de propriété, valeur)
			bernard.setProperty("NomCourt", "DOUSSE1");
			
			Node vsst = db.createNode(NodeLabels.JOURNAL);
			vsst.setProperty("NomJounal", "VSST");
			vsst.setProperty("Edition", 2015);
			vsst.setProperty("Editeur", "Elsevier");
			
			Node ie = db.createNode(NodeLabels.MOT_CLE);
			ie.setProperty("Mot-Cle", "Intelligence Economique");
			
			Node fr = db.createNode(NodeLabels.PAYS);
			fr.setProperty("NomPays", "France");
			
			Relationship appartenir = bernard.createRelationshipTo(fr, TypeRelation.APPARTENANCE); // Création d'une nouvelle relation
																								   // noeud_source.createRelationshipTo(noeud_destination, type_relation);
			appartenir.setProperty("Annee de Naissance", 1947); // Ajout d'une propriété à cette relation, vous pouvez en ajouter autant que vous voulez
			
			Relationship reviewer = bernard.createRelationshipTo(vsst, TypeRelation.REVIEWING);
			reviewer.setProperty("Nbre Avis Positifs", 16);
			
			Relationship interet = bernard.createRelationshipTo(ie, TypeRelation.INTERET);
			interet.setProperty("Nbre Articles Publies", 20);
			
			tx.success();
			
		}
		
		System.out.println("Done!");

	}

}
