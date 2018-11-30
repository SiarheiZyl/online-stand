package com.tsystems.stand.bean.jsf;

import com.tsystems.stand.bean.ejb.ItemTop;
import com.tsystems.stand.model.Item;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 * Backing bean.
 *
 * @author Siarhei
 * @version 1.0
 */
@ManagedBean
@SessionScoped
public class ItemList implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * Injected bean which stores top products values.
     * See {@link ItemTop}
     */
    @EJB
    private ItemTop itemTop;

    /**
     * Simple getter.
     * @return tops that the application stores.
     */
    public List<Item> getItemTop() {
        return itemTop.getTops();
    }
}
