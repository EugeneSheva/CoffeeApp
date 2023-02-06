package com.example.coffeapp.Coffee.Service;
import com.example.coffeapp.Coffee.Model.Product.Product;
import com.example.coffeapp.Coffee.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.sql.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    protected String dbHost = "localhost";
    protected String dbPort = "3306";
    protected String dbUser = "root";
    protected String dbPass = "root";
    protected String dbName = "coffee_app";
    private String connectionAddress = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;


    private final ProductRepository productRepository;


    public Product findById (Long id) { return productRepository.getReferenceById(id); }

    public List<Product> findAll() { return productRepository.findAll(); }

    public Product save(Product product) { return productRepository.save(product); }

    public void deleteById(Long id) { productRepository.deleteById(id); }


    private Connection getConnect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(connectionAddress, dbUser, dbPass);
//            System.out.println("Connection complete.");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
//            System.out.println("Can not connect!");
        }
        return null;
    }




    public String getType(long id) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnect();
            statement = connection.prepareStatement("SELECT type FROM coffee_app.product WHERE id = " + id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String type = rs.getString(1);
                return type;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getType(Product product) {
        Connection connection = null;
        PreparedStatement statement = null;
        long id = product.getId();

        try {
            connection = getConnect();
            statement = connection.prepareStatement("SELECT type FROM coffee_app.product WHERE id = " + id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String type = rs.getString(1);
                return type;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
