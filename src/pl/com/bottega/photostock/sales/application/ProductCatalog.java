package pl.com.bottega.photostock.sales.application;

import pl.com.bottega.photostock.sales.model.*;

import java.util.*;

public class ProductCatalog {

    private ProductRepository repository;

    public ProductCatalog(ProductRepository repository){
        this.repository = repository;
    }

    public List<Picture> find(Client client, Set<String> tags, Money from, Money to){

        return repository.find(client, tags, from, to);
    }



    /*find(null, null, null);
    find(tagi, null, null);
    find(tagi, null, kwota);*/

}
