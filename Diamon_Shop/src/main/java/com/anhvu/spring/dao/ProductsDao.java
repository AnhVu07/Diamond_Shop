/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anhvu.spring.dao;

import com.anhvu.spring.dto.ProductDto;
import com.anhvu.spring.entity.Products;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public class ProductsDao {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<ProductDto> getListProducts() {
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
                    + "group by p.id_products, c.id_product";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                ProductDto e = new ProductDto(rs.getLong("id_productt"), rs.getInt("id_category"), rs.getString("size"), rs.getString("name"), rs.getDouble("price"), rs.getInt("sale"), rs.getString("title"), rs.getBoolean("highlight"), rs.getBoolean("new_product"), rs.getString("detail"), rs.getInt("id_color"), rs.getString("name_color"), rs.getString("code_color"), rs.getString("image"), rs.getDate("created_at"), rs.getDate("update_at"));
                list.add(e);
            }

        } catch (SQLException e) {
        }
        return list;
    }

    public List<ProductDto> getListPByPanigationHome(int start, int end) {
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
                    + "AND p.highlight = 1\n"
                    + "LIMIT ?, ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, start);
            ps.setInt(2, end);
            rs = ps.executeQuery();
            while (rs.next()) {
                ProductDto e = new ProductDto(rs.getLong("id_productt"), rs.getInt("id_category"), rs.getString("size"), rs.getString("name"), rs.getDouble("price"), rs.getDouble("old_price"), rs.getInt("sale"), rs.getString("title"), rs.getBoolean("highlight"), rs.getBoolean("new_product"), rs.getString("detail"), rs.getInt("id_color"), rs.getString("name_color"), rs.getString("code_color"), rs.getString("image"), rs.getDate("created_at"), rs.getDate("update_at"));
                list.add(e);
            }

        } catch (SQLException e) {
        }
        return list;
    }
}
