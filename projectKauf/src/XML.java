import Items.Drink.Bottle;
import Items.Drink.Draft;
import Items.Food.Fruit;
import Items.Food.Pastry;
import Items.Food.Sweets;
import Items.Goods;
import Items.Item;
import bill.Bill;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
public class XML {
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.newDocument();

    Element Bill = doc.createElement("bill");
        doc.appendChild(bill.Bill);

    Element items = doc.createElement("items");
        Bill.appendChild(items);

        for (Item i : bill.getList()) {
        Element item = doc.createElement("item");
        items.appendChild(item);

        Element name = doc.createElement("name");
        name.appendChild(doc.createTextNode(i.getName()));
        item.appendChild(name);

        Element price = doc.createElement("price");
        price.appendChild(doc.createTextNode(String.valueOf(i.getPrice())));
        item.appendChild(price);

        if (i instanceof Fruit) {
            Element weigth = doc.createElement("weight");
            weigth.appendChild(doc.createTextNode(String.valueOf(((Fruit) i).getWeight())));
            item.appendChild(weigth);
        } else if (i instanceof Draft) {
            Element volume = doc.createElement("volume");
            volume.appendChild(doc.createTextNode(String.valueOf(((Draft) i).getVolume())));
            item.appendChild(volume);
        } else if (i instanceof Goods) {
            Element amount = doc.createElement("pieces");
            amount.appendChild(doc.createTextNode(String.valueOf(((Goods) i).getAmount())));
            item.appendChild(amount);
        } else if (i instanceof Pastry) {
            Element amount = doc.createElement("pieces");
            amount.appendChild(doc.createTextNode(String.valueOf(((Pastry) i).getAmount())));
            item.appendChild(amount);
        } else if (i instanceof Sweets) {
            Element amount = doc.createElement("pieces");
            amount.appendChild(doc.createTextNode(String.valueOf((((Sweets) i).getAmount()))));
            item.appendChild(amount);
        } else if (i instanceof Bottle) {
            Element amount = doc.createElement("pieces");
            amount.appendChild(doc.createTextNode(String.valueOf((((Bottle) i).getAmount()))));
            item.appendChild(amount);
        }
    }

    Element totalPrice = doc.createElement("totalPrice");
        Bill.appendChild(totalPrice);

    Element eur = doc.createElement("EUR");
        eur.appendChild(doc.createTextNode(String.valueOf(bill.getFinalPrice())));
        totalPrice.appendChild(eur);

    Element usd = doc.createElement("USD");
        usd.appendChild(doc.createTextNode(String.valueOf(USD)));
        totalPrice.appendChild(usd);

    Element date = doc.createElement("date");
        date.appendChild(doc.createTextNode(String.valueOf(bill.getDate())));
        Bill.appendChild(date);

        return doc;
}

    public void crateXMLFile(Document doc) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("bill.xml"));
        transformer.transform(source, result);
}
