    package com.automaatio.model.database;

    import jakarta.persistence.EntityManager;
    import jakarta.persistence.TypedQuery;
    import java.util.List;

    /**
     * @author Matleena Kankaanpää
     * 26.9.2023
     *
     * DAO for Routine class
     */

    public class RoutineDAO {

        /**
         * Adds a new routine
         * @param routine A new routine
         */
        public void addRoutine(Routine routine) {
            EntityManager em = MysqlDBJpaConn.getInstance();
            em.getTransaction().begin();
            try {
                em.persist(routine);
                em.getTransaction().commit();
            } catch (Exception e) {
                em.getTransaction().rollback();
                throw e; // Rethrow the exception for the caller to handle
            } finally {
                em.close();
            }
        }

        /**
         * Fetches all routines
         * @return A list of Routine objects
         */
        public List<Routine> getAll() {
            EntityManager em = MysqlDBJpaConn.getInstance();
            em.getTransaction().begin();
            try {
                TypedQuery<Routine> query = em.createQuery("SELECT r FROM Routine r", Routine.class);
                List<Routine> routines = query.getResultList();
                em.getTransaction().commit();
                return routines;
            } catch (Exception e) {
                em.getTransaction().rollback();
                throw e; // Rethrow the exception for the caller to handle
            } finally {
                em.close();
            }
        }

        /**
         * Fetches routines by device ID
         * @param deviceId The ID of the device
         * @return A list of Routine objects for the specified device
         */
        public List<Routine> getRoutinesByDeviceId(int deviceId) {
            EntityManager em = MysqlDBJpaConn.getInstance();
            em.getTransaction().begin();
            try {
                TypedQuery<Routine> query = em.createQuery("SELECT r FROM Routine r WHERE r.deviceID.id = :deviceId", Routine.class)
                        .setParameter("deviceId", deviceId);
                List<Routine> routines = query.getResultList();
                em.getTransaction().commit();
                return routines;
            } catch (Exception e) {
                em.getTransaction().rollback();
                throw e;
            } finally {
                em.close();
            }
        }

        /**
         * @param id ID of the routine
         * @param automated Boolean indicating whether the routine is automatized or not
         */
        public void toggleOnOff(int id, boolean automated) {
            EntityManager em = MysqlDBJpaConn.getInstance();
            em.getTransaction().begin();

            try {
                Routine routine = em.find(Routine.class, id);
                routine.setAutomated(!automated);
                System.out.println("updated routine");
                em.getTransaction().commit();
            } catch (Exception e) {
                em.getTransaction().rollback();
                throw e;
            } finally {
                em.close();
            }
        }

        public void setAutomated(int id, boolean setTo) {
            EntityManager em = MysqlDBJpaConn.getInstance();
            em.getTransaction().begin();

            try {
                Routine routine = em.find(Routine.class, id);
                routine.setAutomated(setTo);
                System.out.println("updated routine");
                em.getTransaction().commit();
            } catch (Exception e) {
                em.getTransaction().rollback();
                throw e;
            } finally {
                em.close();
            }
        }

        /**
         * Deletes a routine from the database
         * @param id ID of the routine to delete
         */
        public void deleteRoutine(int id) {
            EntityManager em = MysqlDBJpaConn.getInstance();
            em.getTransaction().begin();
            try {
                Routine routine = em.find(Routine.class, id);
                if (routine != null) {
                    em.remove(routine);
                    System.out.println("routine " + id + " deleted");
                } else {
                    throw new IllegalArgumentException("routine with id  " + id + " was not found");
                }
                em.getTransaction().commit();
            } catch (Exception e) {
                em.getTransaction().rollback();
                throw e;
            } finally {
                em.close();
            }
        }
    }
