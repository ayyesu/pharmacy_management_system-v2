package org.pharmacy.pharmacymgtsys.dao;

import org.pharmacy.pharmacymgtsys.model.Purchase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class PurchaseDAO {
    public PurchaseDAO() {
    }

    public void addPurchase(Purchase purchase) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();

        try {
            String query = "INSERT INTO purchases (drug_id, customer_name, purchase_date, quantity, total_price) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);

            try {
                stmt.setInt(1, purchase.getDrugId());
                stmt.setString(2, purchase.getCustomerName());
                stmt.setTimestamp(3, Timestamp.valueOf(purchase.getPurchaseDate()));
                stmt.setInt(4, purchase.getQuantity());
                stmt.setDouble(5, purchase.getTotalPrice());
                stmt.executeUpdate();
            } catch (Throwable var9) {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (Throwable var8) {
                        var9.addSuppressed(var8);
                    }
                }

                throw var9;
            }

            if (stmt != null) {
                stmt.close();
            }
        } catch (Throwable var10) {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Throwable var7) {
                    var10.addSuppressed(var7);
                }
            }

            throw var10;
        }

        if (conn != null) {
            conn.close();
        }

    }

    public List<Purchase> getPurchasesByDrugId(int drugId) throws SQLException {
        List<Purchase> purchases = new ArrayList();
        String query = "SELECT * FROM purchases WHERE drug_id = ? ORDER BY purchase_date DESC";
        Connection conn = DatabaseConnection.getConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement(query);

            try {
                stmt.setInt(1, drugId);
                ResultSet rs = stmt.executeQuery();

                while(rs.next()) {
                    Purchase purchase = new Purchase();
                    purchase.setPurchaseId(rs.getInt("purchase_id"));
                    purchase.setDrugId(rs.getInt("drug_id"));
                    purchase.setCustomerName(rs.getString("customer_name"));
                    purchase.setPurchaseDate(rs.getTimestamp("purchase_date").toLocalDateTime());
                    purchase.setQuantity(rs.getInt("quantity"));
                    purchase.setTotalPrice(rs.getDouble("total_price"));
                    purchases.add(purchase);
                }
            } catch (Throwable var10) {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (Throwable var9) {
                        var10.addSuppressed(var9);
                    }
                }

                throw var10;
            }

            if (stmt != null) {
                stmt.close();
            }
        } catch (Throwable var11) {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Throwable var8) {
                    var11.addSuppressed(var8);
                }
            }

            throw var11;
        }

        if (conn != null) {
            conn.close();
        }

        return purchases;
    }

    public List<Purchase> getAllPurchases() throws SQLException {
        List<Purchase> purchases = new ArrayList();
        String query = "SELECT p.purchase_id, p.drug_id, p.customer_name, p.purchase_date, p.quantity, p.total_price, d.drug_name AS drug_name FROM purchases p JOIN drugs d ON p.drug_id = d.drug_id ORDER BY p.purchase_date DESC";
        Connection conn = DatabaseConnection.getConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement(query);

            try {
                ResultSet rs = stmt.executeQuery();

                while(rs.next()) {
                    Purchase purchase = new Purchase();
                    purchase.setPurchaseId(rs.getInt("purchase_id"));
                    purchase.setDrugId(rs.getInt("drug_id"));
                    purchase.setCustomerName(rs.getString("customer_name"));
                    purchase.setPurchaseDate(rs.getTimestamp("purchase_date").toLocalDateTime());
                    purchase.setQuantity(rs.getInt("quantity"));
                    purchase.setTotalPrice(rs.getDouble("total_price"));
                    purchase.setDrugName(rs.getString("drug_name"));
                    purchases.add(purchase);
                }
            } catch (Throwable var9) {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (Throwable var8) {
                        var9.addSuppressed(var8);
                    }
                }

                throw var9;
            }

            if (stmt != null) {
                stmt.close();
            }
        } catch (Throwable var10) {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Throwable var7) {
                    var10.addSuppressed(var7);
                }
            }

            throw var10;
        }

        if (conn != null) {
            conn.close();
        }

        return purchases;
    }
}
