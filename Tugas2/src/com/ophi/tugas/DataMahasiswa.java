package com.ophi.tugas;

import java.util.ArrayList;
import java.util.Scanner;

public class DataMahasiswa {

    public static void main(String[] args) {

        ArrayList<String> nama = new ArrayList<>();
        ArrayList<String> nim = new ArrayList<>();
        ArrayList<String> alamat = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        int pilih;

        do {
            System.out.println("\nAplikasi Data Mahasiswa");
            System.out.println("=======================");

            System.out.println("1.Tambahkan Data Mahasiswa");
            System.out.println("2.Tampilkan Data Mahasiswa");
            System.out.println("3.Cari Data Mahasiswa");
            System.out.println("4.Hapus Data Mahasiswa");
            System.out.println("5.Keluar");

            System.out.print("\nPilih menu : ");
            pilih = input.nextInt();

            if (pilih == 1){
                System.out.print("Masukan Nama : ");
                String NAMA = input.next();
                nama.add(NAMA);

                System.out.print("Masukan NIM : ");
                String NIM = input.next();
                nim.add(NIM);

                System.out.print("Masukan Alamat : ");
                String ALAMAT = input.next();
                alamat.add(ALAMAT);
            }

            else if (pilih == 2){
                System.out.println("\nData Mahasiswa");
                for (int i = 0; i < nim.size(); i++) {
                    System.out.println(i + 1 + "." + nim.get(i));
                }
                System.out.println("\nPilih menu (3) untuk melihat data lengkap");
            }

            else if (pilih == 3){
                System.out.print("Data nomor berapa yang ingin dicari : ");
                int cari = input.nextInt();
                if (cari == 1){
                    System.out.println("\nNama : " + nama.get(0));
                    System.out.println("NIM : " + nim.get(0));
                    System.out.println("Alamat : " + alamat.get(0));
                }

                else if (cari == 2){
                    System.out.println("\nNama : " + nama.get(1));
                    System.out.println("NIM : " + nim.get(1));
                    System.out.println("Alamat : " + alamat.get(1));
                }

                else if (cari == 3){
                    System.out.println("\nNama : " + nama.get(2));
                    System.out.println("NIM : " + nim.get(2));
                    System.out.println("Alamat : " + alamat.get(2));
                }

                else if (cari == 4){
                    System.out.println("\nNama : " + nama.get(3));
                    System.out.println("NIM : " + nim.get(3));
                    System.out.println("Alamat : " + alamat.get(3));
                }

                else if (cari == 5){
                    System.out.println("\nNama : " + nama.get(4));
                    System.out.println("NIM : " + nim.get(4));
                    System.out.println("Alamat : " + alamat.get(4));
                }

                else {
                    System.err.println("Data Tidak Ada!");
                }
            }

            else if (pilih == 4){
                System.out.println("\nData Mahasiswa");
                for (int i = 0; i < nim.size(); i++) {
                    System.out.println(i + 1 + "." + nim.get(i));
                }
                System.out.print("\nMasukan NIM untuk menghapus data : ");
                String NIM = input.next();
                nim.remove(NIM);
            }

            else if (pilih == 5){
                System.out.println("Terima Kasih");
            }

            else {
                System.err.println("Menu Tidak Tersedia!");
            }
        } while (pilih != 5);
    }
}
