package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("===TEST 1: seller findById===");
		Seller seller = sellerDao.findById(3);
		
		System.out.println();
		
		System.out.println("===TEST 2: seller findByDepartment===");
		Department department = new Department(2,null);
		List<Seller> list = sellerDao.findByDepartment(department);
		for(Seller obj : list) {
			System.out.println(obj);
		}
		System.out.println();
		System.out.println("===TEST 3: seller findAll===");
		list = sellerDao.findAll();

		for(Seller obj : list) {
			System.out.println(obj);
		}
		
		//System.out.println("===TEST 4: seller Insert===");
		//Seller newSeller = new Seller(null,"greg","greg@gmail.com",new Date(),4000.0,department);
		//sellerDao.insert(newSeller);
		//System.out.println("Inserted "+ newSeller.getId());
		
		//System.out.println("===TEST 5: seller Update===");
		//seller = sellerDao.findById(10);
		//seller.setName("AVA MAX");   //altera o nome do objeto em memória
		//sellerDao.update(seller);
		//System.out.println("update completed");
		
		System.out.println("===TEST 6: seller Delete===");
		sellerDao.deleteById(9);
		System.out.println("delete completed");
		

		
		
		
		
	}

}
