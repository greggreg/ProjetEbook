package test;

import java.io.FileOutputStream;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class JDom1
{
	//Nous allons commencer notre arborescence en créant la racine XML
	//qui sera ici "personnes".
	static Element racine = new Element("personnes");

	//On crée un nouveau Document JDOM basé sur la racine que l'on vient de créer
	static org.jdom2.Document document = new Document(racine);

	public static void main(String[] args)
	{
		//On crée un nouvel Element etudiant et on l'ajoute
		//en tant qu'Element de racine
		Element etudiant = new Element("etudiant");
		racine.addContent(etudiant);

		//On crée un nouvel Attribut classe et on l'ajoute à etudiant
		//grâce à la méthode setAttribute
		Attribute classe = new Attribute("classe","P2");
		etudiant.setAttribute(classe);

		//On crée un nouvel Element nom, on lui assigne du texte
		//et on l'ajoute en tant qu'Element de etudiant
		Element nom = new Element("nom");
		nom.setText("CynO");
		etudiant.addContent(nom);

		//Les deux méthodes qui suivent seront définies plus loin dans l'article
		affiche();
		enregistre("res/Exercice1.xml");
	}

	//Ajouter ces deux méthodes à notre classe JDOM1
	static void affiche()
	{
		try
		{
			//On utilise ici un affichage classique avec getPrettyFormat()
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(document, System.out);
		}
		catch (java.io.IOException e){}
	}

	static void enregistre(String fichier)
	{
		try
		{
			//On utilise ici un affichage classique avec getPrettyFormat()
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			//Remarquez qu'il suffit simplement de créer une instance de FileOutputStream
			//avec en argument le nom du fichier pour effectuer la sérialisation.
			sortie.output(document, new FileOutputStream(fichier));
		}
		catch (java.io.IOException e){}
	}
}