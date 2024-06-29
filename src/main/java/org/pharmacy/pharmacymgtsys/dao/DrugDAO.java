package org.pharmacy.pharmacymgtsys.dao;

import org.pharmacy.pharmacymgtsys.model.Drug;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * This is the Data Access Object for the Drugs
 * It provides and controls the drug data
 *
 * @author Daniel, jonathan, Hannah, felix, Martin, Bright
 * @version 1.0
 */
public class DrugDAO {
    public DrugDAO() {
    }

    /**
     * This method gets adds drugs to the record in the database.
     * If drug already exists the stock is updated by adding existing/previous
     * stock value to the new stock value
     *
     * @param drug represents the drug objects
     */
    public void addDrug(Drug drug) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();

        try {
            // Check if the drug already exists
            String checkQuery = "SELECT stock FROM drugs WHERE drug_name = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setString(1, drug.getDrugName());

            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                // Drug exists, update the stock
                int currentStock = rs.getInt("stock");
                int newStock = currentStock + drug.getStock();
                String updateQuery = "UPDATE drugs SET stock = ? WHERE drug_name = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                updateStmt.setInt(1, newStock);
                updateStmt.setString(2, drug.getDrugName());
                updateStmt.executeUpdate();
                updateStmt.close();
            } else {
                // Drug doesn't exist, insert a new record
                String insertQuery = "INSERT INTO drugs (drug_name, description, price, stock) VALUES (?, ?, ?, ?)";
                PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
                insertStmt.setString(1, drug.getDrugName());
                insertStmt.setString(2, drug.getDescription());
                insertStmt.setDouble(3, drug.getPrice());
                insertStmt.setInt(4, drug.getStock());
                insertStmt.executeUpdate();
                insertStmt.close();
            }

            checkStmt.close();
            rs.close();
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

    /**
     * This method gets all drugs record in the database and stores it in an ArrayList
     *
     * @return It returns all drug records from an ArrayList
     */
    public List<Drug> getAllDrugs() throws SQLException {
        List<Drug> drugs = new ArrayList();
        Connection conn = DatabaseConnection.getConnection();

        try {
            String query = "SELECT * FROM drugs";
            Statement stmt = conn.createStatement();

            try {
                ResultSet rs = stmt.executeQuery(query);

                try {
                    while(rs.next()) {
                        Drug drug = new Drug();
                        drug.setDrugId(rs.getInt("drug_id"));
                        drug.setDrugName(rs.getString("drug_name"));
                        drug.setDescription(rs.getString("description"));
                        drug.setPrice(rs.getDouble("price"));
                        drug.setStock(rs.getInt("stock"));
                        drugs.add(drug);
                    }
                } catch (Throwable var11) {
                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (Throwable var10) {
                            var11.addSuppressed(var10);
                        }
                    }

                    throw var11;
                }

                if (rs != null) {
                    rs.close();
                }
            } catch (Throwable var12) {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (Throwable var9) {
                        var12.addSuppressed(var9);
                    }
                }

                throw var12;
            }

            if (stmt != null) {
                stmt.close();
            }
        } catch (Throwable var13) {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Throwable var8) {
                    var13.addSuppressed(var8);
                }
            }

            throw var13;
        }

        if (conn != null) {
            conn.close();
        }

        return drugs;
    }

    /**
     * @deprecated
     */
    public Drug getDrugById(int drugId) throws SQLException {
        Drug drug = null;
        Connection conn = DatabaseConnection.getConnection();

        try {
            String query = "SELECT * FROM drugs WHERE drug_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);

            try {
                stmt.setInt(1, drugId);
                ResultSet rs = stmt.executeQuery();

                try {
                    if (rs.next()) {
                        drug = new Drug();
                        drug.setDrugId(rs.getInt("drug_id"));
                        drug.setDrugName(rs.getString("drug_name"));
                        drug.setDescription(rs.getString("description"));
                        drug.setPrice(rs.getDouble("price"));
                        drug.setStock(rs.getInt("stock"));
                    }
                } catch (Throwable var12) {
                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (Throwable var11) {
                            var12.addSuppressed(var11);
                        }
                    }

                    throw var12;
                }

                if (rs != null) {
                    rs.close();
                }
            } catch (Throwable var13) {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (Throwable var10) {
                        var13.addSuppressed(var10);
                    }
                }

                throw var13;
            }

            if (stmt != null) {
                stmt.close();
            }
        } catch (Throwable var14) {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Throwable var9) {
                    var14.addSuppressed(var9);
                }
            }

            throw var14;
        }

        if (conn != null) {
            conn.close();
        }

        return drug;
    }

    /**
     * This method takes a name and returns the drug from the database
     *
     * @param drugName This represents the name of the drug that needs to be fetched fom the database
     *
     * @return If drug exists we return the drug object else an exception is thrown
     */
    public Drug getDrugByName(String drugName) throws SQLException {
        Drug drug = null;
        Connection conn = DatabaseConnection.getConnection();

        try {
            String query = "SELECT * FROM drugs WHERE drug_name = ?";
            PreparedStatement stmt = conn.prepareStatement(query);

            try {
                stmt.setString(1, drugName);
                ResultSet rs = stmt.executeQuery();

                try {
                    if (rs.next()) {
                        drug = new Drug();
                        drug.setDrugId(rs.getInt("drug_id"));
                        drug.setDrugName(rs.getString("drug_name"));
                        drug.setDescription(rs.getString("description"));
                        drug.setPrice(rs.getDouble("price"));
                        drug.setStock(rs.getInt("stock"));
                    }
                } catch (Throwable var12) {
                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (Throwable var11) {
                            var12.addSuppressed(var11);
                        }
                    }

                    throw var12;
                }

                if (rs != null) {
                    rs.close();
                }
            } catch (Throwable var13) {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (Throwable var10) {
                        var13.addSuppressed(var10);
                    }
                }

                throw var13;
            }

            if (stmt != null) {
                stmt.close();
            }
        } catch (Throwable var14) {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Throwable var9) {
                    var14.addSuppressed(var9);
                }
            }

            throw var14;
        }

        if (conn != null) {
            conn.close();
        }

        return drug;
    }

    /**
     * This method takes a drug objects and updates the drug field in the database
     *
     * @param drug This represents the drug object to be updated
     *
     */
    public void updateDrug(Drug drug) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();

        try {
            String query = "UPDATE drugs SET drug_name = ?, description = ?, price = ?, stock = ? WHERE drug_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);

            try {
                stmt.setString(1, drug.getDrugName());
                stmt.setString(2, drug.getDescription());
                stmt.setDouble(3, drug.getPrice());
                stmt.setInt(4, drug.getStock());
                stmt.setInt(5, drug.getDrugId());
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
}
