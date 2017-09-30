package pl.com.bottega.photostock.sales.infrastructure.repositories;

import pl.com.bottega.photostock.sales.model.Client;
import pl.com.bottega.photostock.sales.model.Money;
import pl.com.bottega.photostock.sales.model.Picture;
import pl.com.bottega.photostock.sales.model.Product;
import pl.com.bottega.photostock.sales.model.repositories.ClientRepository;
import pl.com.bottega.photostock.sales.model.repositories.ProductRepository;

import java.io.*;
import java.util.*;
import java.util.function.Supplier;

public class CSVProductRepository implements ProductRepository {

    private String path = "C:\\Users\\Lenovo\\Desktop\\products.csv";
    private ClientRepository clientRepository = new inMemoryClientRepository();


    public CSVProductRepository(String path, ClientRepository clientRepository) {
        this.path = path;
        this.clientRepository = clientRepository;
    }

    public Client findClient(String number) {
        if (number.equals("null"))
            return null;
        else
            return clientRepository.get(number);
    }


    @Override
    public Product get(Long number) {
        return getOptional(number).orElseThrow((Supplier<RuntimeException>)
                () -> new IllegalArgumentException("No such product in repo."));
    }

    @Override
    public Optional<Product> getOptional(Long number) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineSplit = line.split(",");
                Product product = makeProductFromLine(lineSplit);
                if (lineSplit[0].equals(number.toString()))
                    return Optional.of(product);
            }
                return Optional.empty();
            }
              catch(FileNotFoundException e){
                return Optional.empty();
            } catch(IOException e){
                throw new RuntimeException(e);
            }
    }

    private Product makeProductFromLine(String[] lineSplit) {
            Long nr = Long.parseLong(lineSplit[0]);
            String[] tags = lineSplit[1].split(";");
            Money price = Money.valueOf(Integer.parseInt(lineSplit[2]));
            boolean active = Boolean.valueOf(lineSplit[3]);
            String reserveByNumber = lineSplit[4];
            String ownerNumber = lineSplit[5];

            return new Picture(nr,
                    tags,
                    price,
                    findClient(reserveByNumber),
                    findClient(ownerNumber),
                    active);
        }


    @Override
    public void save(Product product) {

        Map<Long, Product> map = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineSplit = line.split(",");
                product = makeProductFromLine(lineSplit);
                if (product.getNumber() != null) {
                    map.put(product.getNumber(), product);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        map.put(product.getNumber(), product);

        try(OutputStream outputStream = new FileOutputStream(path);
            PrintStream ps = new PrintStream(outputStream)){

            for (Product p : map.values()){
                ps.println(String.join(",", toLine(p)));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] toLine(Product p){
        String[] line = null;
        line[0] = String.valueOf(p.getNumber());
        line[1] = null;
        if (p instanceof Picture) {
            Picture picture = (Picture) p;
            line[1] = String.join(";", picture.getTags());
        }
        line[2] = p.getPrice().toString();
        line[3] =  p.getActive().toString();
        line[4] = p.getReservedBy();
        line[5] = p.getOwner();

        return line;
        }

        @Override
    public List<Product> find(Client client, Set<String> tags, Money from, Money to) {
        List<Product> results = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] lineSplit = line.split(",");
                Product product = makeProductFromLine(lineSplit);
                if (product instanceof Picture) {
                    Picture picture = (Picture) product;

                    if (matchesCriteria(picture, client, tags, from, to))
                        results.add(picture);
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return results;
    }

    private boolean matchesCriteria(Picture picture, Client client, Set<String> tags, Money from, Money to){

        if(tags != null && !picture.hasTags(tags))
            return false;

        Money price = picture.calculatePrice(client);

        if(from != null && from.gt(price))
            return false;

        if(to != null && to.lt(price))
            return false;

        return true;
    }




}