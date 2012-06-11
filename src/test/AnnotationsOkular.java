package test;

import java.io.FileOutputStream;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class AnnotationsOkular {

	static Element racine = new Element("documentInfo");
	static org.jdom2.Document document = new Document(racine);
	
	public AnnotationsOkular()
	{
		Element pageList = new Element("pageList");
		Element page = new Element("page");
		Attribute number = new Attribute("number","0");
		Element annotationList = new Element("annotationList");
		Element annotation = new Element("annotation");
		Attribute typeA = new Attribute("type","4"); //annotation
//		Attribute typeF = new Attribute("type","6"); //freehand
		Element base = new Element("base");
		Attribute creationDate = new Attribute("creationDate","");
		Attribute uniqueName = new Attribute("uniqueName","");
		Attribute author = new Attribute("author","");
		Attribute contents = new Attribute("contents","");
		Attribute modifyDate = new Attribute("modifyDate","");
		Attribute color = new Attribute("color","");
		Element boundary = new Element("boundary"); //contour de la note (en %)
		Attribute l = new Attribute("l",""); //left
		Attribute r = new Attribute("r",""); //right
		Attribute b = new Attribute("b",""); //bottom
		Attribute t = new Attribute("t",""); //top
		Element hl = new Element("hl");
		Element quad = new Element("quad");
		Attribute ax = new Attribute("ax","");
		Attribute ay = new Attribute("ay","");
		Attribute bx = new Attribute("bx","");
		Attribute by = new Attribute("by","");
		Attribute cx = new Attribute("cx",""); //left
		Attribute cy = new Attribute("cy",""); //right
		Attribute dx = new Attribute("dx",""); //bottom
		Attribute dy = new Attribute("dy","");
		Attribute feather = new Attribute("feather","");
		
		racine.addContent(pageList);
		pageList.addContent(page);
		page.setAttribute(number);
		page.addContent(annotationList);
		annotationList.addContent(annotation);
		annotation.setAttribute(typeA);
//		annotation.setAttribute(typeF);
		annotation.addContent(base);
		base.setAttribute(creationDate);
		base.setAttribute(uniqueName);
		base.setAttribute(author);
		base.setAttribute(contents);
		base.setAttribute(modifyDate);
		base.setAttribute(color);
		base.addContent(boundary);
		boundary.setAttribute(l);
		boundary.setAttribute(r);
		boundary.setAttribute(b);
		boundary.setAttribute(t);
		annotation.addContent(hl);
		hl.addContent(quad);
		quad.setAttribute(ax);
		quad.setAttribute(ay);
		quad.setAttribute(bx);
		quad.setAttribute(by);
		quad.setAttribute(cx);
		quad.setAttribute(cy);
		quad.setAttribute(dx);
		quad.setAttribute(dy);
		quad.setAttribute(feather);
	}
	
	
	public static void main(String[] args)
	{
		new AnnotationsOkular();
		affiche();
		enregistre("res/JDom1.xml");
	}

	static void affiche()
	{
		try
		{
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(document, System.out);
		}
		catch (java.io.IOException e){}
	}

	static void enregistre(String fichier)
	{
		try
		{
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(document, new FileOutputStream(fichier));
		}
		catch (java.io.IOException e)
		{
			System.out.println("Erreur à l'enregistrement du XML");
		}
	}
}

/*	<!--Les freehand Ebook-->
<annotation type="6">
<base creationDate="" uniqueName="id2" author="" modifyDate="" color="#aaffaa">
 <boundary l="" r="" b="" t=""/>
 <penStyle width="2" ycr="0" style="1" spaces="0" marks="3" xcr="0"/>
</base>
<ink>
 <path>
  <point x="" y=""/>
		<!--...-->
  <point x="" y=""/>
 </path>
</ink>
</annotation>

</annotationList>
</page>
</pageList>*/