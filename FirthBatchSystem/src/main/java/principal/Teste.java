package principal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Teste {
	public static void main(String[] args) {
		//Inserindo dados
		try {
			Banco b = new Banco();
			b.criarConexao();
			CustomerAccountDAO DAO = new CustomerAccountDAO(b);
			
			int N = 100;
			String[] nomes = {"José", "Maria", "João", "Chico", "Zé", "Luan", "Ana", "Dona"};
			Random gerador = new Random();
			List<Integer> ids = new ArrayList<Integer>();

			System.out.println("id_customer | nm_customer | cpf_cnpj | is_active | vl_total");
			for (int i = 0; i <= N; i++) {
				int id_customer =  gerador.nextInt(2700);
				
				while(ids.contains(id_customer)){
					id_customer =  gerador.nextInt(2700);
				}
				
				ids.add(id_customer);
				String nm_customer = nomes[gerador.nextInt(nomes.length)];
				int vl_total = gerador.nextInt(1120);
				int cpf_cnpj =  gerador.nextInt(1234567891);
				boolean is_active = id_customer%2==0;
				
				System.out.println(id_customer+" | "+
								   nm_customer+" | "+
								   cpf_cnpj+" | "+
								   is_active+" | "+vl_total);
				DAO.persist(id_customer, cpf_cnpj, nm_customer, is_active, vl_total);
			}
			
			System.out.println("---------------------------------------------------------");
			
			//Mostrando a média de 
			System.out.println("Média do vl_total: "+DAO.findAVG());
			System.out.println("Ultilizando esses clientes abaixo ");
			System.out.println("---------------------------------------------------------");
			
			//Mostrando clientes ordenados pelo saldo
			ResultSet rs = DAO.findPeopleByVL();
			System.out.println("id_customer | nm_customer | cpf_cnpj | is_active | vl_total");
			while (rs.next()) {
				System.out.println(rs.getInt("id_customer")+" | "+
						   rs.getString("nm_customer")+" | "+
						   rs.getInt("cpf_cnpj")+" | "+
						   rs.getBoolean("is_active")+" | "+rs.getInt("vl_total"));
			}
			
			b.closeAll();
			DAO.pstmt.close();
			DAO.stmt.close();
			System.out.println("Conexão fechada!");
		} catch (SQLException e) {
			System.out.println("Deu erro :("+e.getMessage());
		}
	}
}
