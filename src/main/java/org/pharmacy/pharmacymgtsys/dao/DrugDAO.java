package org.pharmacy.pharmacymgtsys.dao;

import org.pharmacy.pharmacymgtsys.model.Drug;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DrugDAO {
    public DrugDAO() {
    }

    public void addDrug(Drug drug) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();

        try {
            String query = "INSERT INTO drugs (drug_name, description, price, stock) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);

            try {
                stmt.setString(1, drug.getDrugName());
                stmt.setString(2, drug.getDescription());
                stmt.setDouble(3, drug.getPrice());
                stmt.setInt(4, drug.getStock());
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
