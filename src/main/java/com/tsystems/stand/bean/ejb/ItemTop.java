package com.tsystems.stand.bean.ejb;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.tsystems.stand.model.Item;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Bean that stores  top itemss.
 * When stand-app got message from JMS server it will make a request
 * to the main application in order to get Updated top of items.
 *
 * @author Siarhei
 * @version 1.0
 */
@Singleton
public class ItemTop implements Serializable {

    private static final Logger log = Logger.getLogger(ItemTop.class);

    /**
     * List of the top items which objects is shown in advertising stand.
     */
    private List<Item> tops = new ArrayList<>();

    /**
     * Empty constructor.
     */
    public ItemTop() {
    }

    /**
     * Initializer after starting of the Stand application. It gets top products.
     */
    @PostConstruct
    public void init() {
        updateTopProducts();
        log.info("Application was successfully started and has got current top products list.");
    }

    /**
     * Simple setter.
     * @return top items list.
     */
    public List<Item> getTops() {
        return tops;
    }

    /**
     * Simple setter.
     * @param tops that must be set.
     */
    public void setTops(List<Item> tops) {
        this.tops = tops;
    }

    /**
     * Making a request to the main application in order
     * to update top items list. Method is getting a response and trying to parse it.
     */
    public void updateTopProducts() {
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

        Client client = Client.create(clientConfig);
        WebResource webResource = client.resource("http://localhost:8080/advertising/stand");

        log.info("Connection was opened.");

        ClientResponse response = webResource
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);

        log.info("Stand have got response without any errors.");


        List list = response.getEntity(ArrayList.class);
        try {
            tops = parseListOfProducts(list);
            log.info("Stand application successfully has parsed list of top products and has set it as current.");
        } catch (Exception e) {
            log.info("During the parsing process some critical situation was occurred.", e);
        }
    }

    /**
     * Method that parses received list of top items from the main application.
     * @param list with received items.
     * @return parsed List of top items.
     */
    private List<Item> parseListOfProducts(List<Map<String, String>> list) {
        List<Item> tops = new ArrayList<>(10);
        for(Map<String, String> product : list) {
            Long id = null;
            String name = null;
            String price = null;
            String numberOfSales = null;
            for(Map.Entry<String, String> attribute : product.entrySet()) {
                if (attribute.getKey().equals("id")) {
                    id = Long.parseLong(attribute.getValue());
                }
                if (attribute.getKey().equals("name")) {
                    name = attribute.getValue();
                }
                if (attribute.getKey().equals("price")) {
                    price = attribute.getValue();
                }
                if (attribute.getKey().equals("numberOfSales")) {
                    numberOfSales = attribute.getValue();
                }
            }
            Item tmp = new Item(id, name, price, "/image/" + id, numberOfSales);
            tops.add(tmp);
        }
        return tops;
    }
}
