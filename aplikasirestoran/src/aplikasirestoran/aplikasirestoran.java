package aplikasirestoran;

import java.util.ArrayList;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

class Menu {
	
	private String nama;
	private double harga;
	private String kategori;
	
	public Menu(String nama, double harga, String kategori) {
		
		this.nama = nama;
		this.harga = harga;
		this.kategori = kategori;
		
		}
	

	public String getnama() {
		return nama;
	}

	public double getharga() {
		return harga;
	}

	public String getkategori() {
		return kategori;
	}


public static Menu[] getdaftarmenuList() {
	Menu[] isimenu = {
			new Menu("Nasi Goreng	", 20000, "Makanan"),
			new Menu("Nasi Padang	", 25000, "Makanan"),
			new Menu("Nasi Uduk	", 15000, "Makanan"),
			new Menu("Nasi Bakar	", 23000, "Makanan"),
			new Menu("Nasi Rames	", 17000, "Makanan"),
			
			new Menu("Es Teh Manis	", 10000, "Minuman"),
			new Menu("Es Teh Lemon	", 12000, "Minuman"),
			new Menu("Es Jeruk	", 12000, "Minuman"),
			new Menu("Es Cappucinno", 18000, "Minuman"),
			new Menu("Air Mineral	", 6000, "Minuman")
	};
	return isimenu;
	
}

}

public class aplikasirestoran {	
	
	private static Scanner input = new Scanner(System.in);
	private static int totalqty = 0;
	private static int total_biaya = 0;
	private static final double x = 0.1;
	private static final double biaya_pelayanan = 20000;
	private static ArrayList<String> daftarpesanan = new ArrayList<>();
	private static Menu[] isimenu;
//	
	
	public static void main(String[] args) {
		System.out.println("Selamat Datang di Aplikasi Restoran");
		System.out.println();
		isimenu = Menu.getdaftarmenuList();
		aplikasirestoran a = new aplikasirestoran();
		a.menukategori(isimenu);
	}
	
	public static void menukategori(Menu[] isimenu) {
	
		if (totalqty >= 4) {
			System.out.println("Batas pesanan maksimal 4 menu");
			return;
		}
		
		System.out.println("======= Menu Utama =======");
		System.out.println("[1] Daftar Menu Makanan");
		System.out.println("[2] Daftar Menu Minuman");
		System.out.println("[0] Keluar Aplikasi");
		System.out.println();
		System.out.print("Pilihlah kode menu yang Anda inginkan (0-2) : ");
		String pilihmenu = input.nextLine();

		
		switch (pilihmenu) {
		case "1" :
			System.out.println();
			System.out.println("========== Menu Makanan ==========");
			System.out.println();
			menampilkanmenu(isimenu, "Makanan", 0);
			pemesanan(isimenu, "Makanan", 0);
			break;
		case "2" : 
			System.out.println();
			System.out.println("========== Menu Minuman ==========");
			menampilkanmenu(isimenu, "Minuman", 0);
			pemesanan(isimenu, "Minuman", 0);
			break;
		case "0" :
			System.out.println("Anda telah keluar aplikasi...");
			ulangprogram();
			break;
		default :
			System.out.println();
			System.out.println("Kode menu yang Anda masukkan salah!");
			System.out.println();
			menukategori(isimenu);
		} 
	}
	
	
	public static void menampilkanmenu(Menu[] isimenu, String kategori, int idx) {
		
		DecimalFormat kursIndonesia = (DecimalFormat)
				DecimalFormat.getCurrencyInstance();
				DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
		
		formatRp.setCurrencySymbol("Rp. ");
		
			formatRp.setMonetaryDecimalSeparator(',');
				formatRp.setGroupingSeparator('.');
				kursIndonesia.setDecimalFormatSymbols(formatRp);
		
		if(idx < isimenu.length) {
			if(isimenu[idx].getkategori().equals(kategori)) {
				System.out.println(idx + 1 +". " + isimenu[idx].getnama() + "	" +kursIndonesia.format(isimenu[idx].getharga()));
			}
			menampilkanmenu(isimenu, kategori, idx+1);	
		}
	}
	
	
	public static void pemesanan(Menu[] isimenu, String kategori, int itemqty) {
		
		DecimalFormat kursIndonesia = (DecimalFormat)
				DecimalFormat.getCurrencyInstance();
				DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
		
		formatRp.setCurrencySymbol("Rp. ");
		
			formatRp.setMonetaryDecimalSeparator(',');
				formatRp.setGroupingSeparator('.');
				kursIndonesia.setDecimalFormatSymbols(formatRp);
				
		if (itemqty >= 4 || totalqty >= 4) {
			System.out.println();
			System.out.println("ANDA TELAH MENCAPAI BATAS MAKSIMAL PEMESANAN!");
			struk();
			return;
		}
		
		System.out.println();
		System.out.println("Ketik 'x' untuk keluar aplikasi atau ketik 's' untuk menampilkan struk");
		System.out.print("Masukkan kode menu yang ingin dipesan (Maks. 4 menu) : ");
		String dataorder = input.nextLine();
		
		switch (dataorder) {
		case "x" :
			System.out.println("Anda telah keluar aplikasi!");
			break;
		case "s" :
			struk();
			return;
		}
	
		if(!dataorder.equalsIgnoreCase("x")) {
			try {
				int pilihmenu = Integer.parseInt(dataorder);
				if (pilihmenu >= 1 && pilihmenu <= isimenu.length) {
					
					int itemX = pilihmenu-1;
					if(isimenu[itemX].getkategori().equals(kategori)) {
						
						System.out.print("Jumlah yang ingin Anda pesan : ");
						int jum = Integer.parseInt(input.nextLine());
						
										
					Menu menudipilih = isimenu[itemX];	
						double totalitem = menudipilih.getharga()*jum;
						
						daftarpesanan.add(menudipilih.getnama()+ " " +kursIndonesia.format(menudipilih.getharga()) + " X " +jum + " = " + kursIndonesia.format(totalitem));
						System.out.println();
						System.out.println("Pesanan Anda berhasil ditambahkan!");
						System.out.println(""+menudipilih.getnama() + " = " + jum);
						System.out.println("Total harga pesanan : " +kursIndonesia.format(totalitem));
						
						totalqty++;
						total_biaya += totalitem;
					} else {
						System.out.println("Kode menu makanan/minuman yang anda input tidak sesuai!");
						
					}
					
				} else {
					System.out.println("Menu yang Anda pilih salah!");
				}
				
			} catch (NumberFormatException e) {
				System.out.println("Pilihan yang Anda input salah!");
			}
			pemesanan(isimenu, kategori, itemqty+1);	
		
		} 
	
	}
	
	
	public static void struk() {
				
		DecimalFormat kursIndonesia = (DecimalFormat)
				DecimalFormat.getCurrencyInstance();
				DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
		
		formatRp.setCurrencySymbol("Rp. ");
		
			formatRp.setMonetaryDecimalSeparator(',');
				formatRp.setGroupingSeparator('.');
				kursIndonesia.setDecimalFormatSymbols(formatRp);
		
		System.out.println();
		System.out.println("===================== STRUK PESANAN ANDA =====================");
		System.out.println();
		System.out.println("Makanan/Minuman yang Anda pesan : ");
		for (String dataorder : daftarpesanan) {
			System.out.println(dataorder);
		}
		
		
		if (total_biaya > 100000) {
			System.out.println();
			System.out.println("Total Bayar : " +kursIndonesia.format(total_biaya));
			
			double diskon = total_biaya*x;
			double total_akhirstlhdiskon = total_biaya-diskon;
			System.out.println();
			System.out.println("Selamat! Anda mendapatkan diskon 10%");
			System.out.println("Total diskon 10% = "+kursIndonesia.format(diskon));
			System.out.println("Total bayar setelah mendapatkan diskon : "+kursIndonesia.format(total_akhirstlhdiskon));
			
			System.out.println();
			double pajak = total_biaya*x;
			System.out.println("Berlaku pajak 10%");
			System.out.println("Total biaya pajak : "+kursIndonesia.format(pajak));
			
			System.out.println();
			System.out.println("Berlaku biaya pelayanan : "+kursIndonesia.format(biaya_pelayanan));
			
			double totalakhir2 = total_akhirstlhdiskon+pajak+biaya_pelayanan;
			System.out.println();
			System.out.println("Total bayar akhir = "+kursIndonesia.format(totalakhir2));
			System.out.println();
			
		} else if(total_biaya > 50000 && totalqty>=3) {
			
			System.out.println();
			System.out.println("Total bayar awal : " +kursIndonesia.format(total_biaya));
			
			double pajak = total_biaya*x;
			System.out.println();
			System.out.println("Berlaku pajak 10%");
			System.out.println("Total biaya pajak : "+kursIndonesia.format(pajak));
			
			System.out.println();
			System.out.println("Berlaku biaya pelayanan : "+kursIndonesia.format(biaya_pelayanan));
			
			double totalakhir1 = total_biaya+pajak+biaya_pelayanan;
			System.out.println();
			System.out.println("Total bayar akhir = "+kursIndonesia.format(totalakhir1));
			System.out.println();
			Menu promoMinuman = promo(isimenu, 0);
			if(promoMinuman != null) {
				double diskon = promoMinuman.getharga();
				totalakhir1 -= diskon;
				System.out.println("Selamat Anda mendapatkan promo gratis 1 Es Teh");
				}
			
			} else {
				System.out.println();
				System.out.println("Total bayar awal : " +kursIndonesia.format(total_biaya));
				
				double pajak = total_biaya*x;
				System.out.println();
				System.out.println("Berlaku pajak 10%");
				System.out.println("Total biaya pajak : "+kursIndonesia.format(pajak));
				
				System.out.println();
				System.out.println("Berlaku biaya pelayanan : "+kursIndonesia.format(biaya_pelayanan));
				
				double totalakhir1 = total_biaya+pajak+biaya_pelayanan;
				System.out.println();
				System.out.println("Total bayar akhir = "+kursIndonesia.format(totalakhir1));
				System.out.println();
			}
		
		ulangprogram();
		return;
	}	
	
	public static Menu promo(Menu[] isimenu, int idx) {
		if (idx < isimenu.length) {
			if (isimenu[idx].getkategori().equals("Minuman")) {
				return isimenu[idx];
			}
			return promo(isimenu, idx + 1);
		}
		return null;
	}

	public static void ulangprogram() {
		char ulang;
		System.out.print("Ingin memesan lagi? (y/t) : ");
		ulang = input.next().charAt(0);
		switch (ulang) {
		case 'y' :
			aplikasirestoran ulangprogram1 = new aplikasirestoran();
			ulangprogram1.menukategori(isimenu);
			
			aplikasirestoran ulangprogram2 = new aplikasirestoran();
			ulangprogram2.menampilkanmenu(isimenu, null, total_biaya);
			break;
		case 't' :
			System.out.println();
			System.out.println("Terima kasih atas kunjungan Anda...");
			break;
	
		}
	}
	
}


























