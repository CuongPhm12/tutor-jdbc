package service.product;

import config.ConnectionSingleton;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService{


    @Override
    public List<Product> showAllProduct() {
        List<Product> products = new ArrayList<>();
        Connection connection = ConnectionSingleton.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from tutorjdbc");
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                products.add(new Product(id,name,price,description));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return products;
    }

    public static void main(String[] args) {
        ProductService productService = new ProductService();
       productService.deleteProduct(3);
    }

    @Override
    public void addProduct(Product product) {
        Connection connection = ConnectionSingleton.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into tutorjdbc(name,price,description) values(?,?,?)");
            preparedStatement.setString(1,product.getName());
            preparedStatement.setDouble(2,product.getPrice());
            preparedStatement.setString(3,product.getDescription());
             int rowInserted = preparedStatement.executeUpdate();
            System.out.println(rowInserted);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public Product selectProduct(int id) {
        Product product = null;
        Connection connection = ConnectionSingleton.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from tutorjdbc where id = ?");
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){

                String name = rs.getString("name");
                Double price= rs.getDouble("price");
                String description= rs.getString("description");
                product=new Product(id, name, price,description);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;

    }

    @Override
    public void updateProduct(int id, Product product) {
        Connection connection = ConnectionSingleton.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update tutorjdbc set name =?, price=?,description=? where id =?");


            preparedStatement.setString(1,product.getName());
            preparedStatement.setDouble(2,product.getPrice());
            preparedStatement.setString(3,product.getDescription());
            preparedStatement.setInt(4,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteProduct(int id) {
        Connection connection = ConnectionSingleton.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE from tutorjdbc where id = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Product findById(int id) {
        Product product = null;
        Connection connection = ConnectionSingleton.getConnection();
        PreparedStatement preparedStatement = null;
        try { preparedStatement = connection.prepareStatement("select * from tutorjdbc where id = ?");
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                product = new Product(id,name,price,description);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }
}
