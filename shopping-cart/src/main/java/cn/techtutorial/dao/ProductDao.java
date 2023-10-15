package cn.techtutorial.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.techtutorial.Model.Cart;
import cn.techtutorial.Model.Product;

public class ProductDao 
{
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	public ProductDao(Connection con) { 	
		this.con = con;
	} 	
	
	public List<Product> getAllProducts()
	{
		List<Product> products = new ArrayList<>();
		
		
		try {
			query = "Select * from products"; 	
			pst = this.con.prepareStatement(query);
			rs = pst.executeQuery();
			
			while(rs.next())
			{
				Product row = new Product();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getDouble("price"));
				row.setImage(rs.getString("image"));
				
				products.add(row);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return products; 
	}
	
	
	public List<Cart> getcartProducts(ArrayList<Cart> cartList)
	{
		List<Cart> products = new ArrayList<>();
		
		try 
		{
			if(cartList.size() > 0)
			{
				for(Cart item:cartList)
				{
					query = "select * from products where id=?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs = pst.executeQuery();
					while(rs.next())
					{
						Cart row = new Cart();
						row.setId(rs.getInt("id"));
						row.setName(rs.getString("name"));
						row.setCategory(rs.getString("category"));
						row.setPrice(rs.getDouble("price")*item.getQuantity());
						row.setQuantity(item.getQuantity());
						products.add(row);
					}
				}
			}
		} 
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		
		return products;
	}
	
	public double getTotalcartPrice(ArrayList<Cart> cartList)
	{
		double sum = 0;
		try
		{
			if(cartList.size() > 0)
			{
				for(Cart item : cartList)
				{
					query = "Select price from Products where id=?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs = pst.executeQuery();
					
					while(rs.next())
					{
						sum += rs.getDouble("price")*item.getQuantity();
						
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		return sum;
	}
}
