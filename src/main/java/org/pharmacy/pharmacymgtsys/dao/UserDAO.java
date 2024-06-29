package org.pharmacy.pharmacymgtsys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This is the Data Access Object for the Users
 * It provides and controls the User data
 *
 * @author Daniel, jonathan, Hannah, felix, Martin, Bright
 * @version 1.0
 */
public class UserDAO {
    /**
     * This method creates an instance of a purchase by a customer (simulated)
     *
     * @param username Represents the username received from the user.
     * @param password Represents the password received from the user.
     */
    public boolean validateUser(String username, String password) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();

        boolean var7;
        label100: {
            try {
                PreparedStatement stmt;
                label102: {
                    String query = "SELECT COUNT(*) FROM users WHERE username = ? AND password = ?";
                    stmt = conn.prepareStatement(query);

                    try {
                        stmt.setString(1, username);
                        stmt.setString(2, password);
                        ResultSet rs = stmt.executeQuery();

                        label88: {
                            try {
                                if (rs.next()) {
                                    var7 = rs.getInt(1) > 0;
                                    break label88;
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
                            break label102;
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
                    break label100;
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

            return false;
        }

        if (conn != null) {
            conn.close();
        }

        return var7;
    }
}
