package deseptikon.monya.configuration.io_xml;

import deseptikon.monya.configuration.db.create_tables.parcel_tables.ParcelCreateProvisionalList;
import deseptikon.monya.configuration.spring_jdbc.jdbc.LmstQuery;
import deseptikon.monya.configuration.spring_jdbc.jdbc.parcel.UpdateParcelDAO;
import deseptikon.monya.configuration.spring_jdbc.models.Parcel;
import deseptikon.monya.usage_code_new.hadlers.UC01_010New;
import deseptikon.monya.usage_code_new.hadlers.UC01_180New;
import deseptikon.monya.usage_code_new.hadlers.UC04_040New;
import org.apache.commons.lang3.time.StopWatch;
import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.dhatim.fastexcel.reader.Row;
import org.dhatim.fastexcel.reader.Sheet;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.*;

public class unMarshallerParcel {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc_spring_config.xml");
        UpdateParcelDAO updateParcelsKLADR = (UpdateParcelDAO) context.getBean("dataSourceForJdbcTemplateLMST");


        File directoryPath = new File("\\\\192.168.0.118\\каталог оценщиков\\ОЦЕНКА 2026\\ПЕРЕЧЕНЬ 2026\\Исходные данные\\Выгрузка перечня из НСПД\\XML_Испр\\XML");

        for (File fileXML : Objects.requireNonNull(directoryPath.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File directory, String name) {
                return name.toLowerCase().contains(".xml");
            }
        }))) {
            System.out.println(fileXML.toString());

            updateParcelsKLADR.updateParcelsKLADR (new unMarshallerParcel().parcelXMLFile(fileXML).stream().toList());
        }
//        File[] f = directoryPath.listFiles();
//        List<Parcel> p = new unMarshallerParcel().parcelXMLFile(f[10]).stream().toList();
//        System.out.println(p.getFirst().getCadastralNumber() + " " + p.getFirst().getKLADR());
//        updateParcelsKLADR.updateParcelsKLADR(p);

        stopWatch.stop();
        long timeTaken = stopWatch.getTime();
        System.out.println(timeTaken / 1000 / 60 + " минут");
//        18:16:091001:36 1801700000000
//        18:16:091001:36 1801700000000
    }

    private Set<Parcel> parcelXMLFile(File file) throws ParserConfigurationException, IOException, SAXException {
        Set<Parcel> parcelList = new HashSet<>();
        // Step 1: Create a DocumentBuilderFactory and DocumentBuilder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        // Step 2: Parse the XML file
        Document document = builder.parse(file);
        // Normalize the document
        document.getDocumentElement().normalize();
        // Step 3: Get the root element
        Element root = document.getDocumentElement();
        // Step 4: Get all employee nodes
        NodeList nodeList = root.getElementsByTagName("Parcel");
        // Step 5: Iterate over employee nodes and print information
        int i;
        for (i = 0; i < nodeList.getLength(); i++) {
            Parcel parcel = new Parcel();
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String cadastralNumber = element.getAttribute("CadastralNumber");
                String KLADR;
                try {
                    KLADR = element.getElementsByTagName("KLADR").item(0).getTextContent();
                } catch (NullPointerException e) {
                    KLADR = "";
                }
                if (!KLADR.isEmpty()) {
                    parcel.setCadastralNumber(cadastralNumber);
                    parcel.setKLADR(KLADR);
                    System.out.println(parcel.getCadastralNumber() + " " + parcel.getKLADR());
                    parcelList.add(parcel);
                }
            }
        }
        return parcelList;
    }
}
