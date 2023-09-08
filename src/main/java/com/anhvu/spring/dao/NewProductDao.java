/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anhvu.spring.dao;

import com.anhvu.spring.dto.OrdersDto;
import com.anhvu.spring.dto.ProductDto;
import com.anhvu.spring.entity.Bills;
import com.anhvu.spring.entity.Billsdetail;
import com.anhvu.spring.entity.Contact;
import com.anhvu.spring.entity.ProductReview;
import com.anhvu.spring.entity.Products;
import com.anhvu.spring.entity.Users;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public class NewProductDao {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<ProductDto> getListNewProducts() {
        List<ProductDto> list = new ArrayList<>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diamon_shop", "root", "");
            String query = "select \n"
                    + "p.id_products as id_productt,\n"
                    + "p.id_category,\n"
                    + "p.size,\n"
                    + "p.name,\n"
                    + "p.price,\n"
                    + "p.sale,\n"
                    + "p.title,\n"
                    + "p.highlight,\n"
                    + "p.new_product,\n"
                    + "p.detail,\n"
                    + "c.id as id_color,\n"
                    + "c.name as name_color,\n"
                    + "c.code as code_color,\n"
                    + "c.image,\n"
                    + "p.created_at,\n"
                    + "p.update_at\n"
                    + "from products as p\n"
                    + "inner join colors as c\n"
                    + "on p.id_products = c.id_product\n"
                    + "and p.new_product =1\n"
                    + "group by p.id_products, c.id_product";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                ProductDto e = new ProductDto(rs.getLong("id_productt"), rs.getInt("id_category"), rs.getString("size"), rs.getString("name"), rs.getDouble("price"), rs.getInt("sale"), rs.getString("title"), rs.getBoolean("highlight"), rs.getBoolean("new_product"), rs.getString("detail"), rs.getInt("id_color"), rs.getString("name_color"), rs.getString("code_color"), rs.getString("image"), rs.getDate("created_at"), rs.getDate("update_at"));
                list.add(e);
            }

        } catch (SQLException e) {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public List<ProductDto> getProductsByCategory(String id) {
        List<ProductDto> list = new ArrayList<>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diamon_shop", "root", "");
            String query = "select \n"
                    + "p.id_products as id_productt,\n"
                    + "p.id_category,\n"
                    + "p.size,\n"
                    + "p.name,\n"
                    + "p.price,\n"
                    + "p.sale,\n"
                    + "p.title,\n"
                    + "p.highlight,\n"
                    + "p.new_product,\n"
                    + "p.detail,\n"
                    + "c.id as id_color,\n"
                    + "c.name as name_color,\n"
                    + "c.code as code_color,\n"
                    + "c.image,\n"
                    + "p.created_at,\n"
                    + "p.update_at\n"
                    + "from products as p\n"
                    + "inner join colors as c\n"
                    + "on p.id_products = c.id_product\n"
                    + "and p.id_category =?\n"
                    + "group by p.id_products, c.id_product";
            ps = con.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                ProductDto e = new ProductDto(rs.getLong("id_productt"), rs.getInt("id_category"), rs.getString("size"), rs.getString("name"), rs.getDouble("price"), rs.getInt("sale"), rs.getString("title"), rs.getBoolean("highlight"), rs.getBoolean("new_product"), rs.getString("detail"), rs.getInt("id_color"), rs.getString("name_color"), rs.getString("code_color"), rs.getString("image"), rs.getDate("created_at"), rs.getDate("update_at"));
                list.add(e);
            }
        } catch (SQLException e) {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public ProductDto getProductsById(String id) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diamon_shop", "root", "");
            String query = "select \n"
                    + "p.id_products as id_productt,\n"
                    + "p.id_category,\n"
                    + "p.size,\n"
                    + "p.name,\n"
                    + "p.price,\n"
                    + "p.old_price,\n"
                    + "p.sale,\n"
                    + "p.title,\n"
                    + "p.highlight,\n"
                    + "p.new_product,\n"
                    + "p.detail,\n"
                    + "c.id as id_color,\n"
                    + "c.name as name_color,\n"
                    + "c.code as code_color,\n"
                    + "c.image,\n"
                    + "p.created_at,\n"
                    + "p.update_at\n"
                    + "from products as p\n"
                    + "inner join colors as c\n"
                    + "on p.id_products = c.id_product\n"
                    + "and p.id_products =?\n"
                    + "group by p.id_products, c.id_product";
            ps = con.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new ProductDto(rs.getLong("id_productt"), rs.getInt("id_category"), rs.getString("size"), rs.getString("name"), rs.getDouble("price"), rs.getDouble("old_price"), rs.getInt("sale"), rs.getString("title"), rs.getBoolean("highlight"), rs.getBoolean("new_product"), rs.getString("detail"), rs.getInt("id_color"), rs.getString("name_color"), rs.getString("code_color"), rs.getString("image"), rs.getDate("created_at"), rs.getDate("update_at"));
            }
        } catch (SQLException e) {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public Products getProductsId(String id) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diamon_shop", "root", "");
            String query = "SELECT * FROM products WHERE id_products = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Products(rs.getLong("id_products"), rs.getString("size"), rs.getString("name"), rs.getDouble("price"), rs.getDouble("old_price"), rs.getString("image"), rs.getInt("sale"), rs.getString("title"), rs.getBoolean("highlight"), rs.getBoolean("new_product"), rs.getString("detail"), rs.getDate("created_at"), rs.getDate("update_at"), rs.getInt("id_category"));
            }
        } catch (SQLException e) {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public Products getProductsIdNewLastest() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diamon_shop", "root", "");
            String query = "SELECT * FROM products WHERE id_products =(SELECT MAX(id_products) FROM products)";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Products(rs.getLong("id_products"), rs.getString("size"), rs.getString("name"), rs.getDouble("price"), rs.getDouble("old_price"), rs.getString("image"), rs.getInt("sale"), rs.getString("title"), rs.getBoolean("highlight"), rs.getBoolean("new_product"), rs.getString("detail"), rs.getDate("created_at"), rs.getDate("update_at"), rs.getInt("id_category"));
            }
        } catch (SQLException e) {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public List<ProductDto> getProductsBySearch(String name) {
        List<ProductDto> list = new ArrayList<>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diamon_shop", "root", "");
            String query = "select \n"
                    + "p.id_products as id_productt,\n"
                    + "p.id_category,\n"
                    + "p.size,\n"
                    + "p.name,\n"
                    + "p.price,\n"
                    + "p.sale,\n"
                    + "p.title,\n"
                    + "p.highlight,\n"
                    + "p.new_product,\n"
                    + "p.detail,\n"
                    + "c.id as id_color,\n"
                    + "c.name as name_color,\n"
                    + "c.code as code_color,\n"
                    + "c.image,\n"
                    + "p.created_at,\n"
                    + "p.update_at\n"
                    + "from products as p\n"
                    + "inner join colors as c\n"
                    + "on p.id_products = c.id_product\n"
                    + "WHERE p.name LIKE ?\n"
                    + "group by p.id_products, c.id_product";
            ps = con.prepareStatement(query);
            ps.setString(1, "%" + name + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                ProductDto e = new ProductDto(rs.getLong("id_productt"), rs.getInt("id_category"), rs.getString("size"), rs.getString("name"), rs.getDouble("price"), rs.getInt("sale"), rs.getString("title"), rs.getBoolean("highlight"), rs.getBoolean("new_product"), rs.getString("detail"), rs.getInt("id_color"), rs.getString("name_color"), rs.getString("code_color"), rs.getString("image"), rs.getDate("created_at"), rs.getDate("update_at"));
                list.add(e);
            }
        } catch (SQLException e) {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public List<Products> getListProducts() {
        List<Products> list = new ArrayList<>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diamon_shop", "root", "");
            String query = "SELECT * FROM products";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Products e = new Products(rs.getLong("id_products"), rs.getString("size"), rs.getString("name"), rs.getDouble("price"), rs.getDouble("old_price"), rs.getString("image"), rs.getInt("sale"), rs.getString("title"), rs.getBoolean("highlight"), rs.getBoolean("new_product"), rs.getString("detail"), rs.getDate("created_at"), rs.getDate("update_at"), rs.getInt("id_category"));
                list.add(e);
            }
        } catch (SQLException e) {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public boolean insertUser(Users user) {
        boolean index = false;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diamon_shop", "root", "");
            String query = "INSERT INTO users(user,password,display_name,address,roles) VALUES (?,?,?,?,?)";
            ps = con.prepareCall(query);
            ps.setString(1, user.getUser());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getDisplayName());
            ps.setString(4, user.getAddress());
            ps.setBoolean(5, user.getRoles());
            index = ps.execute();
        } catch (SQLException e) {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return index;
    }

    public boolean insertProduct(int idCategory, String size, String name, double price, int sale, String title, boolean hilight, boolean newProduct, String detail, Date createDate, Date update) {
        boolean index = false;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diamon_shop", "root", "");
            String query = "INSERT INTO products(id_category,size,name,price,sale,title,highlight,new_product,detail,created_at,update_at) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            ps = con.prepareCall(query);
            ps.setInt(1, idCategory);
            ps.setString(2, size);
            ps.setString(3, name);
            ps.setDouble(4, price);
            ps.setInt(5, sale);
            ps.setString(6, title);
            ps.setBoolean(7, hilight);
            ps.setBoolean(8, newProduct);
            ps.setString(9, detail);
            ps.setDate(10, (Date) createDate);
            ps.setDate(11, (Date) update);
            index = ps.execute();
        } catch (SQLException e) {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return index;
    }

    public boolean addProduct(Products products) {
        boolean index = false;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diamon_shop", "root", "");
            String query = "INSERT INTO products(id_products,id_category,size,name,price,old_price,image,sale,title,highlight,new_product,detail,created_at,update_at) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            ps = con.prepareCall(query);
            ps.setLong(1, products.getIdProducts());
            ps.setInt(2, products.getIdCategory());
            ps.setString(3, products.getSize());
            ps.setString(4, products.getName());
            ps.setDouble(5, products.getPrice());
            ps.setDouble(6, products.getOld_price());
            ps.setString(7, products.getImage());
            ps.setInt(8, products.getSale());
            ps.setString(9, products.getTitle());
            ps.setBoolean(10, products.getHighlight());
            ps.setBoolean(11, products.getNewProduct());
            ps.setString(12, products.getDetail());
            ps.setDate(13, (Date) products.getCreatedAt());
            ps.setDate(14, (Date) products.getUpdateAt());
            index = ps.execute();
        } catch (SQLException e) {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return index;
    }

    public boolean insertColor(String image, long id, String name, String code) {
        boolean index = false;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diamon_shop", "root", "");
            String query = "INSERT INTO colors(id_product,name,code,image) VALUES(?,?,?,?)";
            ps = con.prepareCall(query);
//            ps.setInt(1, colors.getId());
            ps.setLong(1, id);
            ps.setString(2, name);
            ps.setString(3, code);
            ps.setString(4, image);
            index = ps.execute();
        } catch (SQLException e) {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return index;
    }

    public Users getUser(String user) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diamon_shop", "root", "");
            String query = "SELECT * FROM users WHERE user =?";
            ps = con.prepareStatement(query);
            ps.setString(1, user);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Users(rs.getInt("id"), rs.getString("user"), rs.getString("password"), rs.getString("display_name"), rs.getBoolean("roles"));
            }
        } catch (SQLException e) {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public Users getAccount(Users user) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diamon_shop", "root", "");
            String query = "SELECT * FROM users WHERE user =? ";
            ps = con.prepareStatement(query);
            ps.setString(1, user.getUser());
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Users(rs.getInt("id"), rs.getString("user"), rs.getString("password"), rs.getString("display_name"), rs.getBoolean("roles"));
            }
        } catch (SQLException e) {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public boolean insertBill(Bills bill) {
        boolean index = false;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diamon_shop", "root", "");
            String query = "INSERT INTO bills(users,phone,display_name,address,total,quatity,note,created_at) VALUES(?,?,?,?,?,?,?,?)";
            ps = con.prepareCall(query);
            ps.setString(1, bill.getUsers());
            ps.setString(2, bill.getPhone());
            ps.setString(3, bill.getDisplayName());
            ps.setString(4, bill.getAddress());
            ps.setDouble(5, bill.getTotal());
            ps.setInt(6, bill.getQuatity());
            ps.setString(7, bill.getNote());
            ps.setDate(8, (Date) bill.getCreated_at());
            index = ps.execute();
        } catch (SQLException e) {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return index;
    }

    public boolean updateProduct(Products products) {
        boolean index = false;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diamon_shop", "root", "");
            String query = "UPDATE products SET id_category=?,size=?,name=?,price=?,old_price=?,image=?,sale=?,title=?,highlight=?,new_product=?,detail=?,created_at=?,update_at=? where id_products = ?";
            ps = con.prepareCall(query);
            ps.setInt(1, products.getIdCategory());
            ps.setString(2, products.getSize());
            ps.setString(3, products.getName());
            ps.setDouble(4, products.getPrice());
            ps.setDouble(5, products.getOld_price());
            ps.setString(6, products.getImage());
            ps.setInt(7, products.getSale());
            ps.setString(8, products.getTitle());
            ps.setBoolean(9, products.getHighlight());
            ps.setBoolean(10, products.getNewProduct());
            ps.setString(11, products.getDetail());
            ps.setDate(12, (Date) products.getCreatedAt());
            ps.setDate(13, (Date) products.getUpdateAt());
            ps.setLong(14, products.getIdProducts());
            index = ps.execute();
        } catch (SQLException e) {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return index;
    }

    public boolean addBillDetail(Billsdetail billdetail) {
        boolean index = false;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diamon_shop", "root", "");
            String query = "INSERT INTO billsdetail(id_products, id_bill, quatity, total) VALUES(?,?,?,?)";
            ps = con.prepareCall(query);
            ps.setLong(1, billdetail.getIdProducts());
            ps.setInt(2, billdetail.getIdBill());
            ps.setInt(3, billdetail.getQuatity());
            ps.setDouble(4, billdetail.getTotal());
            index = ps.execute();
        } catch (SQLException e) {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return index;
    }

    public boolean deleteProduct(int id) {
        boolean index = false;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diamon_shop", "root", "");
            String query = "DELETE FROM products WHERE id_products = ?";
            ps = con.prepareCall(query);
            ps.setInt(1, id);
            index = ps.execute();
        } catch (SQLException e) {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return index;
    }

    public Bills getLastestBill() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diamon_shop", "root", "");
            String query = "SELECT * FROM bills WHERE id = (SELECT MAX(id) FROM bills);";
            ps = con.prepareCall(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Bills(rs.getInt("id"), rs.getString("users"), rs.getString("phone"), rs.getString("display_name"), rs.getString("address"), rs.getDouble("total"), rs.getInt("quatity"), rs.getString("note"), rs.getDate("created_at"));
            }
        } catch (SQLException e) {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public List<ProductDto> getProductsByPagination(String id, int start, int end) {
        List<ProductDto> list = new ArrayList<>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diamon_shop", "root", "");
            String query = "select \n"
                    + "p.id_products as id_productt,\n"
                    + "p.id_category,\n"
                    + "p.size,\n"
                    + "p.name,\n"
                    + "p.price,\n"
                    + "p.old_price,\n"
                    + "p.sale,\n"
                    + "p.title,\n"
                    + "p.highlight,\n"
                    + "p.new_product,\n"
                    + "p.detail,\n"
                    + "c.id as id_color,\n"
                    + "c.name as name_color,\n"
                    + "c.code as code_color,\n"
                    + "c.image,\n"
                    + "p.created_at,\n"
                    + "p.update_at\n"
                    + "from products as p\n"
                    + "inner join colors as c\n"
                    + "on p.id_products = c.id_product\n"
                    + "WHERE 1=1\n"
                    + "and id_category = ?\n"
                    + "LIMIT ?,?";
            ps = con.prepareStatement(query);
            ps.setString(1, id);
            ps.setInt(2, start);
            ps.setInt(3, end);
            rs = ps.executeQuery();
            while (rs.next()) {
                ProductDto e = new ProductDto(rs.getLong("id_productt"), rs.getInt("id_category"), rs.getString("size"), rs.getString("name"), rs.getDouble("price"), rs.getDouble("old_price"), rs.getInt("sale"), rs.getString("title"), rs.getBoolean("highlight"), rs.getBoolean("new_product"), rs.getString("detail"), rs.getInt("id_color"), rs.getString("name_color"), rs.getString("code_color"), rs.getString("image"), rs.getDate("created_at"), rs.getDate("update_at"));
                list.add(e);
            }
        } catch (SQLException e) {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public ProductDto getProductsLatest() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diamon_shop", "root", "");
            String query = "select p.id_products as id_productt, p.id_category, p.size, p.name, p.price, p.sale, p.title, p.highlight, p.new_product, p.detail, c.id as id_color, c.name as name_color, c.code as code_color, c.image, p.created_at, p.update_at from products as p inner join colors as c on p.id_products = c.id_product where 1=1 and p.id_products = (Select MAX(id_products) FROM products) group by p.id_products, c.id_product";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new ProductDto(rs.getLong("id_productt"), rs.getInt("id_category"), rs.getString("size"), rs.getString("name"), rs.getDouble("price"), rs.getInt("sale"), rs.getString("title"), rs.getBoolean("highlight"), rs.getBoolean("new_product"), rs.getString("detail"), rs.getInt("id_color"), rs.getString("name_color"), rs.getString("code_color"), rs.getString("image"), rs.getDate("created_at"), rs.getDate("update_at"));
            }
        } catch (SQLException e) {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public boolean addReview(ProductReview productReview) {
        boolean index = false;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diamon_shop", "root", "");
            String query = "INSERT INTO product_review(id_product, id_user, name, email, evaluate, comment) VALUES(?,?,?,?,?,?)";
            ps = con.prepareCall(query);
            ps.setInt(1, productReview.getIdProduct());
            ps.setObject(2, productReview.getIdUser());
            ps.setString(3, productReview.getName());
            ps.setString(4, productReview.getEmail());
            ps.setString(5, productReview.getEvaluate());
            ps.setString(6, productReview.getComment());
            index = ps.execute();
        } catch (SQLException e) {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return index;
    }

    public boolean addContact(Contact contact) {
        boolean index = false;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diamon_shop", "root", "");
            String query = "INSERT INTO contact(name,email,subject,content) VALUES (?,?,?,?)";
            ps = con.prepareCall(query);
            ps.setString(1, contact.getName());
            ps.setString(2, contact.getEmail());
            ps.setString(3, contact.getSubject());
            ps.setString(4, contact.getContent());
            index = ps.execute();
        } catch (SQLException e) {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return index;
    }

    public List<ProductReview> getProductsReview(String id) {
        List<ProductReview> list = new ArrayList<ProductReview>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diamon_shop", "root", "");
            String query = "select * from product_review WHERE id_product = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                ProductReview e = new ProductReview(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("evaluate"), rs.getString("comment"), rs.getInt("id_user"), rs.getInt("id_product"));
                list.add(e);
            }
        } catch (SQLException e) {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public List<OrdersDto> getListOrders() {
        List<OrdersDto> list = new ArrayList<OrdersDto>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diamon_shop", "root", "");
            String query = "SELECT \n"
                    + "    b.id AS bill_id,\n"
                    + "    b.users,\n"
                    + "    b.phone,\n"
                    + "    b.display_name,\n"
                    + "    b.note,\n"
                    + "    b.address,\n"
                    + "    bd.quatity AS quantity,\n"
                    + "    bd.total AS bill_detail_total,\n"
                    + "    bd.id_products,\n"
                    + "    p.name AS product_name,\n"
                    + "    p.price AS product_price,\n"
                    + "    p.image AS product_image,\n"
                    + "    p.detail AS product_detail,\n"
                    + "    p.id_category\n"
                    + "FROM\n"
                    + "    bills b\n"
                    + "    INNER JOIN billsdetail bd ON b.id = bd.id_bill\n"
                    + "    INNER JOIN products p ON bd.id_products = p.id_products";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                OrdersDto e = new OrdersDto(rs.getInt("bill_id"), rs.getString("users"), rs.getString("phone"), rs.getString("display_name"), rs.getString("note"),
                        rs.getString("address"), rs.getInt("quantity"), rs.getDouble("bill_detail_total"), rs.getLong("id_products"), rs.getString("product_name"),
                        rs.getDouble("product_price"), rs.getString("product_image"), rs.getString("product_detail"), rs.getInt("id_category"));
                list.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public List<String[]> getDataOrders() {
        List<String[]> list = new ArrayList<String[]>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diamon_shop", "root", "");
            String query = "SELECT DATE(`created_at`) AS ngay, COUNT(`id`) AS total_orders, SUM(`total`) AS total_revenue FROM `bills` GROUP BY DATE(`created_at`) ORDER BY DATE(`created_at`)";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int total_orders = rs.getInt("total_orders");
                double total_revenue = rs.getDouble("total_revenue");
                java.util.Date ngay = rs.getDate("ngay");
                String pattern = "yyyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                String dateString = sdf.format(ngay);
                String[] row = {Integer.toString(total_orders), Double.toString(total_revenue), dateString};
                list.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public boolean deleteBills(int id) {
        boolean index = false;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diamon_shop", "root", "");
            String query = "DELETE FROM bills WHERE id = ?";
            ps = con.prepareCall(query);
            ps.setInt(1, id);
            index = ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return index;
    }

    public List<Contact> getListContacts() {
        List<Contact> list = new ArrayList<>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diamon_shop", "root", "");
            String query = "SELECT * FROM contact";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Contact e = new Contact(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("subject"), rs.getString("content")
                );
                list.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    public boolean deleteContact(int id) {
        boolean index = false;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diamon_shop", "root", "");
            String query = "DELETE FROM contact WHERE id = ?";
            ps = con.prepareCall(query);
            ps.setInt(1, id);
            index = ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return index;
    }
}
