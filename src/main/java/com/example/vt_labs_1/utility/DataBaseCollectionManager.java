package com.example.vt_labs_1.utility;

import com.example.vt_labs_1.data.*;
import com.example.vt_labs_1.exceptions.DatabaseHandlingException;
import com.example.vt_labs_1.exceptions.IncorrectData;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Operates the database collection itself.
 */
public class DataBaseCollectionManager {
    // MOVIE_TABLE
    private final String SELECT_ALL_MOVIES = "SELECT * FROM " + DataBaseHandler.MOVIE_TABLE;
    private final String SELECT_MOVIE_BY_ID = SELECT_ALL_MOVIES + " WHERE " +
            DataBaseHandler.MOVIE_TABLE_ID_COLUMN + " = ?";
    private final String SELECT_MOVIE_BY_ID_AND_USER_ID = SELECT_MOVIE_BY_ID + " AND " +
            DataBaseHandler.MOVIE_TABLE_USER_ID_COLUMN + " = ?";
    private final String INSERT_MOVIE = "INSERT INTO " +
            DataBaseHandler.MOVIE_TABLE + " (" +
//            DataBaseHandler.MOVIE_TABLE_ID_COLUMN + ", " +
            DataBaseHandler.MOVIE_TABLE_NAME_COLUMN + ", " +
            DataBaseHandler.MOVIE_TABLE_CREATION_DATE_COLUMN + ", " +
            DataBaseHandler.MOVIE_TABLE_OSCARSCOUNT_COLUMN + ", " +
            DataBaseHandler.MOVIE_TABLE_GENRE_COLUMN + ", " +
            DataBaseHandler.MOVIE_TABLE_MPAA_RATING_COLUMN + ", " +
            DataBaseHandler.MOVIE_TABLE_COORDINATES_X_COLUMN + ", " +
            DataBaseHandler.MOVIE_TABLE_COORDINATES_Y_COLUMN + ", " +
            DataBaseHandler.MOVIE_TABLE_DIRECTOR_NAME_COLUMN + ", " +
            DataBaseHandler.MOVIE_TABLE_DIRECTOR_HEIGHT_COLUMN + ", " +
            DataBaseHandler.MOVIE_TABLE_DIRECTOR_EYE_COLOR_COLUMN + ", " +
            DataBaseHandler.MOVIE_TABLE_DIRECTOR_HAIR_COLOR_COLUMN + ", " +
            DataBaseHandler.MOVIE_TABLE_DIRECTOR_NATIONALITY_COLUMN + ", " +
            DataBaseHandler.MOVIE_TABLE_DIRECTOR_LOCATION_X_COLUMN + ", " +
            DataBaseHandler.MOVIE_TABLE_DIRECTOR_LOCATION_Y_COLUMN + ", " +
            DataBaseHandler.MOVIE_TABLE_DIRECTOR_LOCATION_Z_COLUMN + ", " +
            DataBaseHandler.MOVIE_TABLE_DIRECTOR_LOCATION_NAME_COLUMN + ", " +
            DataBaseHandler.MOVIE_TABLE_USER_ID_COLUMN + ") VALUES (" +
//            "?," +
            " ?, ?, ?, ?, " +
            "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String DELETE_MOVIE_BY_ID = "DELETE FROM " + DataBaseHandler.MOVIE_TABLE +
            " WHERE " + DataBaseHandler.MOVIE_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_MOVIE_NAME_BY_ID = "UPDATE " + DataBaseHandler.MOVIE_TABLE + " SET " +
            DataBaseHandler.MOVIE_TABLE_NAME_COLUMN + " = ?" + " WHERE " +
            DataBaseHandler.MOVIE_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_MOVIE_CREATION_DATE_BY_ID = "UPDATE " + DataBaseHandler.MOVIE_TABLE + " SET " +
            DataBaseHandler.MOVIE_TABLE_CREATION_DATE_COLUMN + " = ?" + " WHERE " +
            DataBaseHandler.MOVIE_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_MOVIE_OSCARSCOUNT_BY_ID = "UPDATE " + DataBaseHandler.MOVIE_TABLE + " SET " +
            DataBaseHandler.MOVIE_TABLE_OSCARSCOUNT_COLUMN + " = ?" + " WHERE " +
            DataBaseHandler.MOVIE_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_MOVIE_GENRE_BY_ID = "UPDATE " + DataBaseHandler.MOVIE_TABLE + " SET " +
            DataBaseHandler.MOVIE_TABLE_GENRE_COLUMN + " = ?" + " WHERE " +
            DataBaseHandler.MOVIE_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_MOVIE_MPAA_RATING_BY_ID = "UPDATE " + DataBaseHandler.MOVIE_TABLE + " SET " +
            DataBaseHandler.MOVIE_TABLE_MPAA_RATING_COLUMN + " = ?" + " WHERE " +
            DataBaseHandler.MOVIE_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_MOVIE_COORDINATES_X_BY_ID = "UPDATE " + DataBaseHandler.MOVIE_TABLE + " SET " +
            DataBaseHandler.MOVIE_TABLE_COORDINATES_X_COLUMN + " = ?" + " WHERE " +
            DataBaseHandler.MOVIE_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_MOVIE_COORDINATES_Y_BY_ID = "UPDATE " + DataBaseHandler.MOVIE_TABLE + " SET " +
            DataBaseHandler.MOVIE_TABLE_COORDINATES_Y_COLUMN + " = ?" + " WHERE " +
            DataBaseHandler.MOVIE_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_MOVIE_DIRECTOR_NAME_COLUMN = "UPDATE " + DataBaseHandler.MOVIE_TABLE + " SET " +
            DataBaseHandler.MOVIE_TABLE_DIRECTOR_NAME_COLUMN + " = ?" + " WHERE " +
            DataBaseHandler.MOVIE_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_MOVIE_DIRECTOR_HEIGHT_COLUMN = "UPDATE " + DataBaseHandler.MOVIE_TABLE + " SET " +
            DataBaseHandler.MOVIE_TABLE_DIRECTOR_HEIGHT_COLUMN + " = ?" + " WHERE " +
            DataBaseHandler.MOVIE_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_MOVIE_DIRECTOR_EYE_COLOR_COLUMN = "UPDATE " + DataBaseHandler.MOVIE_TABLE + " SET " +
            DataBaseHandler.MOVIE_TABLE_DIRECTOR_EYE_COLOR_COLUMN + " = ?" + " WHERE " +
            DataBaseHandler.MOVIE_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_MOVIE_DIRECTOR_HAIR_COLOR_COLUMN = "UPDATE " + DataBaseHandler.MOVIE_TABLE + " SET " +
            DataBaseHandler.MOVIE_TABLE_DIRECTOR_HAIR_COLOR_COLUMN + " = ?" + " WHERE " +
            DataBaseHandler.MOVIE_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_MOVIE_DIRECTOR_NATIONALITY_COLUMN = "UPDATE " + DataBaseHandler.MOVIE_TABLE + " SET " +
            DataBaseHandler.MOVIE_TABLE_DIRECTOR_NATIONALITY_COLUMN + " = ?" + " WHERE " +
            DataBaseHandler.MOVIE_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_MOVIE_DIRECTOR_LOCATION_X_COLUMN = "UPDATE " + DataBaseHandler.MOVIE_TABLE + " SET " +
            DataBaseHandler.MOVIE_TABLE_DIRECTOR_LOCATION_X_COLUMN + " = ?" + " WHERE " +
            DataBaseHandler.MOVIE_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_MOVIE_DIRECTOR_LOCATION_Y_COLUMN = "UPDATE " + DataBaseHandler.MOVIE_TABLE + " SET " +
            DataBaseHandler.MOVIE_TABLE_DIRECTOR_LOCATION_Y_COLUMN + " = ?" + " WHERE " +
            DataBaseHandler.MOVIE_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_MOVIE_DIRECTOR_LOCATION_Z_COLUMN = "UPDATE " + DataBaseHandler.MOVIE_TABLE + " SET " +
            DataBaseHandler.MOVIE_TABLE_DIRECTOR_LOCATION_Z_COLUMN + " = ?" + " WHERE " +
            DataBaseHandler.MOVIE_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_MOVIE_DIRECTOR_LOCATION_NAME_COLUMN = "UPDATE " + DataBaseHandler.MOVIE_TABLE + " SET " +
            DataBaseHandler.MOVIE_TABLE_DIRECTOR_LOCATION_NAME_COLUMN + " = ?" + " WHERE " +
            DataBaseHandler.MOVIE_TABLE_ID_COLUMN + " = ?";
    private DataBaseHandler databaseHandler;
    private DataBaseUserManager databaseUserManager;

    public DataBaseCollectionManager(DataBaseHandler databaseHandler, DataBaseUserManager databaseUserManager) {
        this.databaseHandler = databaseHandler;
        this.databaseUserManager = databaseUserManager;
    }

    /**
     * Create Marine.
     *
     * @param resultSet Result set parametres of Marine.
     * @return New Marine.
     * @throws SQLException When there's exception inside.
     */
    private Movie createMovie(ResultSet resultSet) {
        try {
            long id = resultSet.getLong(DataBaseHandler.MOVIE_TABLE_ID_COLUMN);
            String name = resultSet.getString(DataBaseHandler.MOVIE_TABLE_NAME_COLUMN);
            Date creationDate = new Date(resultSet.getLong(DataBaseHandler.MOVIE_TABLE_CREATION_DATE_COLUMN));
            Long oscarsCount = resultSet.getLong(DataBaseHandler.MOVIE_TABLE_OSCARSCOUNT_COLUMN);
            MovieGenre movieGenre = MovieGenre.valueOf(resultSet.getString(DataBaseHandler.MOVIE_TABLE_GENRE_COLUMN));
            MpaaRating mpaaRating = MpaaRating.valueOf(resultSet.getString(DataBaseHandler.MOVIE_TABLE_MPAA_RATING_COLUMN));
            Coordinates coordinates = new Coordinates(resultSet.getDouble(DataBaseHandler.MOVIE_TABLE_COORDINATES_X_COLUMN), resultSet.getInt(DataBaseHandler.MOVIE_TABLE_COORDINATES_Y_COLUMN));
            Person director = new Person(resultSet.getString(DataBaseHandler.MOVIE_TABLE_DIRECTOR_NAME_COLUMN),
                    resultSet.getDouble(DataBaseHandler.MOVIE_TABLE_DIRECTOR_HEIGHT_COLUMN),
                    Color.valueOf(resultSet.getString(DataBaseHandler.MOVIE_TABLE_DIRECTOR_EYE_COLOR_COLUMN)),
                    Color.valueOf(resultSet.getString(DataBaseHandler.MOVIE_TABLE_DIRECTOR_HAIR_COLOR_COLUMN)),
                    Country.valueOf(resultSet.getString(DataBaseHandler.MOVIE_TABLE_DIRECTOR_NATIONALITY_COLUMN)),
                    new Location(resultSet.getDouble(DataBaseHandler.MOVIE_TABLE_DIRECTOR_LOCATION_X_COLUMN),
                            resultSet.getDouble(DataBaseHandler.MOVIE_TABLE_DIRECTOR_LOCATION_Y_COLUMN),
                            resultSet.getDouble(DataBaseHandler.MOVIE_TABLE_DIRECTOR_LOCATION_Z_COLUMN),
                            resultSet.getString(DataBaseHandler.MOVIE_TABLE_DIRECTOR_LOCATION_NAME_COLUMN)));
            User owner = databaseUserManager.getUserById(resultSet.getLong(DataBaseHandler.MOVIE_TABLE_USER_ID_COLUMN));
            return new Movie(
                    id,
                    name,
                    coordinates,
                    creationDate,
                    oscarsCount,
                    movieGenre,
                    mpaaRating,
                    director,
                    owner
            );
        } catch (SQLException e){
            return null;
        }
    }

    private String[] createTableMovie(ResultSet resultSet) {
        String[] oneRow = new String[18];
        try {
            oneRow[0] = ((Long) resultSet.getLong(DataBaseHandler.MOVIE_TABLE_ID_COLUMN)).toString();
            oneRow[1] = resultSet.getString(DataBaseHandler.MOVIE_TABLE_NAME_COLUMN);
            oneRow[2] = new Date(resultSet.getLong(DataBaseHandler.MOVIE_TABLE_CREATION_DATE_COLUMN)).toString();
            oneRow[3] = ((Long) resultSet.getLong(DataBaseHandler.MOVIE_TABLE_OSCARSCOUNT_COLUMN)).toString();
            oneRow[4] = resultSet.getString(DataBaseHandler.MOVIE_TABLE_GENRE_COLUMN);
            oneRow[5] = resultSet.getString(DataBaseHandler.MOVIE_TABLE_MPAA_RATING_COLUMN);
            oneRow[6] = ((Double) resultSet.getDouble(DataBaseHandler.MOVIE_TABLE_COORDINATES_X_COLUMN)).toString();
            oneRow[7] = ((Integer) resultSet.getInt(DataBaseHandler.MOVIE_TABLE_COORDINATES_Y_COLUMN)).toString();
            oneRow[8] = resultSet.getString(DataBaseHandler.MOVIE_TABLE_DIRECTOR_NAME_COLUMN);
            oneRow[9] = ((Double) resultSet.getDouble(DataBaseHandler.MOVIE_TABLE_DIRECTOR_HEIGHT_COLUMN)).toString();
            oneRow[10] = resultSet.getString(DataBaseHandler.MOVIE_TABLE_DIRECTOR_EYE_COLOR_COLUMN);
            oneRow[11] = resultSet.getString(DataBaseHandler.MOVIE_TABLE_DIRECTOR_HAIR_COLOR_COLUMN);
            oneRow[12] = resultSet.getString(DataBaseHandler.MOVIE_TABLE_DIRECTOR_NATIONALITY_COLUMN);
            oneRow[13] = ((Double) resultSet.getDouble(DataBaseHandler.MOVIE_TABLE_DIRECTOR_LOCATION_X_COLUMN)).toString();
            oneRow[14] = ((Double) resultSet.getDouble(DataBaseHandler.MOVIE_TABLE_DIRECTOR_LOCATION_Y_COLUMN)).toString();
            oneRow[15] = ((Double)resultSet.getDouble(DataBaseHandler.MOVIE_TABLE_DIRECTOR_LOCATION_Z_COLUMN)).toString();
            oneRow[16] = resultSet.getString(DataBaseHandler.MOVIE_TABLE_DIRECTOR_LOCATION_NAME_COLUMN);
            oneRow[17] = databaseUserManager.getUserById(resultSet.getLong(DataBaseHandler.MOVIE_TABLE_USER_ID_COLUMN)).getUsername();
            return oneRow;
        } catch (SQLException e){
            return null;
        }
    }
    /**
     * @return List of Marines.
     * @throws DatabaseHandlingException When there's exception inside.
     */
    public PriorityBlockingQueue<Movie> getCollection() {
        PriorityBlockingQueue<Movie> moviesCollection = new PriorityBlockingQueue<>();
        PreparedStatement preparedSelectAllStatement = null;
        try {
            preparedSelectAllStatement = databaseHandler.getPreparedStatement(SELECT_ALL_MOVIES, false);
            ResultSet resultSet = preparedSelectAllStatement.executeQuery();
            while (resultSet.next()) {
                moviesCollection.add(createMovie(resultSet));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            databaseHandler.closePreparedStatement(preparedSelectAllStatement);
        }
        return moviesCollection;
    }
    public LinkedList<String[]> getTable() {
        LinkedList<String[]> moviesTable = new LinkedList<>();
        PreparedStatement preparedSelectAllStatement = null;
        try {
            preparedSelectAllStatement = databaseHandler.getPreparedStatement(SELECT_ALL_MOVIES, false);
            ResultSet resultSet = preparedSelectAllStatement.executeQuery();
            while (resultSet.next()) {
                moviesTable.add(createTableMovie(resultSet));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            databaseHandler.closePreparedStatement(preparedSelectAllStatement);
        }
        return moviesTable;
    }

    /**
     * @param newMovie Marine raw.
     * @param user     User.
     * @return Marine.
     * @throws DatabaseHandlingException When there's exception inside.
     */
    public Movie insertMovie(Movie newMovie, User user) throws DatabaseHandlingException {
        PreparedStatement preparedInsertMovieStatement = null;
        try {
            databaseHandler.setCommitMode();
            databaseHandler.setSavepoint();
            preparedInsertMovieStatement = databaseHandler.getPreparedStatement(INSERT_MOVIE, true);
//            preparedInsertMovieStatement.setLong(1, newMovie.getId());
            preparedInsertMovieStatement.setString(1, newMovie.getName());
            preparedInsertMovieStatement.setLong(2, newMovie.getCreationDate().getTime());
            preparedInsertMovieStatement.setLong(3, newMovie.getOscarsCount());
            preparedInsertMovieStatement.setString(4, newMovie.getGenre().toString());
            preparedInsertMovieStatement.setString(5, newMovie.getMpaaRating().toString());
            preparedInsertMovieStatement.setDouble(6, newMovie.getCoordinates().getX());
            preparedInsertMovieStatement.setInt(7, newMovie.getCoordinates().getY());
            preparedInsertMovieStatement.setString(8, newMovie.getDirector().getName());
            preparedInsertMovieStatement.setDouble(9, newMovie.getDirector().getHeight());
            preparedInsertMovieStatement.setString(10, String.valueOf(newMovie.getDirector().getEyeColor()));
            preparedInsertMovieStatement.setString(11, String.valueOf(newMovie.getDirector().getHairColor()));
            preparedInsertMovieStatement.setString(12, String.valueOf(newMovie.getDirector().getNationality()));
            preparedInsertMovieStatement.setDouble(13, newMovie.getDirector().getLocation().getX());
            preparedInsertMovieStatement.setDouble(14, newMovie.getDirector().getLocation().getY());
            preparedInsertMovieStatement.setDouble(15, newMovie.getDirector().getLocation().getZ());
            preparedInsertMovieStatement.setString(16, newMovie.getDirector().getLocation().getName());
            preparedInsertMovieStatement.setLong(17, databaseUserManager.getUserIdByUsername(user));
            if (preparedInsertMovieStatement.executeUpdate() == 0) throw new SQLException();
            ResultSet generatedMoviekeys = preparedInsertMovieStatement.getGeneratedKeys();
            long movieID;
            if(generatedMoviekeys.next()){
                movieID = generatedMoviekeys.getLong(1);
            } else throw new SQLException();
            databaseHandler.commit();
            newMovie.setOwner(user);
            newMovie.setId(movieID);
            return newMovie;
        } catch (SQLException exception) {
            exception.printStackTrace();
            databaseHandler.rollback();
            throw new DatabaseHandlingException();
        } catch (IncorrectData e) {
            throw new RuntimeException(e);
        } finally {
            databaseHandler.closePreparedStatement(preparedInsertMovieStatement);
            databaseHandler.setNormalMode();
        }
    }

    /**
     * @param newMovie movie raw.
     * @param movieId  Id of Movie.
     * @throws DatabaseHandlingException When there's exception inside.
     */
    public void updateMovieById(long movieId, Movie newMovie) throws DatabaseHandlingException {
        PreparedStatement preparedUpdateMovieNameByIdStatement = null;
        PreparedStatement preparedUpdateMovieCreationDateByIdStatement = null;
        PreparedStatement preparedUpdateMovieOscarsCountByIdStatement = null;
        PreparedStatement preparedUpdateMovieGenreByIdStatement = null;
        PreparedStatement preparedUpdateMovieMpaaRatingByIdStatement = null;
        PreparedStatement preparedUpdateMovieCoordinatesXByIdStatement = null;
        PreparedStatement preparedUpdateMovieCoordinatesYByIdStatement = null;
        PreparedStatement preparedUpdateMovieDirectorNameByIdStatement = null;
        PreparedStatement preparedUpdateMovieDirectorHeightByIdStatement = null;
        PreparedStatement preparedUpdateMovieDirectorEyeColorByIdStatement = null;
        PreparedStatement preparedUpdateMovieDirectorHairColorByIdStatement = null;
        PreparedStatement preparedUpdateMovieDirectorNationalityByIdStatement = null;
        PreparedStatement preparedUpdateMovieDirectorLocationXByIdStatement = null;
        PreparedStatement preparedUpdateMovieDirectorLocationYByIdStatement = null;
        PreparedStatement preparedUpdateMovieDirectorLocationZByIdStatement = null;
        PreparedStatement preparedUpdateMovieDirectorLocationNameByIdStatement = null;
        try {
            databaseHandler.setCommitMode();
            databaseHandler.setSavepoint();

            preparedUpdateMovieNameByIdStatement = databaseHandler.getPreparedStatement(UPDATE_MOVIE_NAME_BY_ID, false);
            preparedUpdateMovieCreationDateByIdStatement = databaseHandler.getPreparedStatement(UPDATE_MOVIE_CREATION_DATE_BY_ID, false);
            preparedUpdateMovieOscarsCountByIdStatement = databaseHandler.getPreparedStatement(UPDATE_MOVIE_OSCARSCOUNT_BY_ID, false);
            preparedUpdateMovieGenreByIdStatement = databaseHandler.getPreparedStatement(UPDATE_MOVIE_GENRE_BY_ID, false);
            preparedUpdateMovieMpaaRatingByIdStatement = databaseHandler.getPreparedStatement(UPDATE_MOVIE_MPAA_RATING_BY_ID, false);
            preparedUpdateMovieCoordinatesXByIdStatement = databaseHandler.getPreparedStatement(UPDATE_MOVIE_COORDINATES_X_BY_ID, false);
            preparedUpdateMovieCoordinatesYByIdStatement = databaseHandler.getPreparedStatement(UPDATE_MOVIE_COORDINATES_Y_BY_ID, false);
            preparedUpdateMovieDirectorNameByIdStatement = databaseHandler.getPreparedStatement(UPDATE_MOVIE_DIRECTOR_NAME_COLUMN, false);
            preparedUpdateMovieDirectorHeightByIdStatement = databaseHandler.getPreparedStatement(UPDATE_MOVIE_DIRECTOR_HEIGHT_COLUMN, false);
            preparedUpdateMovieDirectorEyeColorByIdStatement = databaseHandler.getPreparedStatement(UPDATE_MOVIE_DIRECTOR_EYE_COLOR_COLUMN, false);
            preparedUpdateMovieDirectorHairColorByIdStatement = databaseHandler.getPreparedStatement(UPDATE_MOVIE_DIRECTOR_HAIR_COLOR_COLUMN, false);
            preparedUpdateMovieDirectorNationalityByIdStatement = databaseHandler.getPreparedStatement(UPDATE_MOVIE_DIRECTOR_NATIONALITY_COLUMN, false);
            preparedUpdateMovieDirectorLocationXByIdStatement = databaseHandler.getPreparedStatement(UPDATE_MOVIE_DIRECTOR_LOCATION_X_COLUMN, false);
            preparedUpdateMovieDirectorLocationYByIdStatement = databaseHandler.getPreparedStatement(UPDATE_MOVIE_DIRECTOR_LOCATION_Y_COLUMN, false);
            preparedUpdateMovieDirectorLocationZByIdStatement = databaseHandler.getPreparedStatement(UPDATE_MOVIE_DIRECTOR_LOCATION_Z_COLUMN, false);
            preparedUpdateMovieDirectorLocationNameByIdStatement = databaseHandler.getPreparedStatement(UPDATE_MOVIE_DIRECTOR_LOCATION_NAME_COLUMN, false);

            if (newMovie.getName() != null) {
                preparedUpdateMovieNameByIdStatement.setString(1, newMovie.getName());
                preparedUpdateMovieNameByIdStatement.setLong(2, movieId);
                if (preparedUpdateMovieNameByIdStatement.executeUpdate() == 0) throw new SQLException();
            }
            if (newMovie.getCreationDate() != null) {
                preparedUpdateMovieCreationDateByIdStatement.setLong(1, newMovie.getCreationDate().getTime());
                preparedUpdateMovieCreationDateByIdStatement.setLong(2, movieId);
                if (preparedUpdateMovieCreationDateByIdStatement.executeUpdate() == 0) throw new SQLException();
            }
            if (newMovie.getOscarsCount() > 0) {
                preparedUpdateMovieOscarsCountByIdStatement.setLong(1, newMovie.getOscarsCount());
                preparedUpdateMovieOscarsCountByIdStatement.setLong(2, movieId);
                if (preparedUpdateMovieOscarsCountByIdStatement.executeUpdate() == 0) throw new SQLException();
            }
            if (newMovie.getGenre() != null) {
                preparedUpdateMovieGenreByIdStatement.setString(1, String.valueOf(newMovie.getGenre()));
                preparedUpdateMovieGenreByIdStatement.setLong(2, movieId);
                if (preparedUpdateMovieGenreByIdStatement.executeUpdate() == 0) throw new SQLException();
            }
            if (newMovie.getMpaaRating() != null) {
                preparedUpdateMovieMpaaRatingByIdStatement.setString(1, String.valueOf(newMovie.getMpaaRating()));
                preparedUpdateMovieMpaaRatingByIdStatement.setLong(2, movieId);
                if (preparedUpdateMovieMpaaRatingByIdStatement.executeUpdate() == 0) throw new SQLException();
            }
            if (newMovie.getCoordinates() != null) {
                preparedUpdateMovieCoordinatesXByIdStatement.setDouble(1, newMovie.getCoordinates().getX());
                preparedUpdateMovieCoordinatesXByIdStatement.setLong(2, movieId);
                preparedUpdateMovieCoordinatesYByIdStatement.setInt(1, newMovie.getCoordinates().getY());
                preparedUpdateMovieCoordinatesYByIdStatement.setLong(2, movieId);
                if (preparedUpdateMovieCoordinatesXByIdStatement.executeUpdate() == 0 || preparedUpdateMovieCoordinatesYByIdStatement.executeUpdate() == 0)
                    throw new SQLException();
            }
            if (newMovie.getDirector() != null) {
                if (newMovie.getDirector().getName() != null) {
                    preparedUpdateMovieDirectorNameByIdStatement.setString(1, newMovie.getDirector().getName());
                    preparedUpdateMovieDirectorNameByIdStatement.setLong(2, movieId);
                    if (preparedUpdateMovieDirectorNameByIdStatement.executeUpdate() == 0) throw new SQLException();
                }
                if (newMovie.getDirector().getHeight() > 0){
                    preparedUpdateMovieDirectorHeightByIdStatement.setDouble(1, newMovie.getDirector().getHeight());
                    preparedUpdateMovieDirectorHeightByIdStatement.setLong(2, movieId);
                    if(preparedUpdateMovieDirectorHeightByIdStatement.executeUpdate() == 0) throw new SQLException();
                }
                if(newMovie.getDirector().getEyeColor() != null){
                    preparedUpdateMovieDirectorEyeColorByIdStatement.setString(1, String.valueOf(newMovie.getDirector().getEyeColor()));
                    preparedUpdateMovieDirectorEyeColorByIdStatement.setLong(2, movieId);
                    if(preparedUpdateMovieDirectorEyeColorByIdStatement.executeUpdate() == 0) throw new SQLException();
                }
                if(newMovie.getDirector().getHairColor() != null){
                    preparedUpdateMovieDirectorHeightByIdStatement.setString(1,String.valueOf(newMovie.getDirector().getHairColor()));
                    preparedUpdateMovieDirectorHeightByIdStatement.setLong(2,movieId);
                    if(preparedUpdateMovieDirectorHairColorByIdStatement.executeUpdate() == 0) throw new SQLException();
                }
                if(newMovie.getDirector().getNationality() != null){
                    preparedUpdateMovieDirectorNationalityByIdStatement.setString(1, String.valueOf(newMovie.getDirector().getNationality()));
                    preparedUpdateMovieDirectorNationalityByIdStatement.setLong(2, movieId);
                    if(preparedUpdateMovieDirectorNationalityByIdStatement.executeUpdate() == 0) throw new SQLException();
                }
                if(newMovie.getDirector().getLocation() != null){
                    if(newMovie.getDirector().getLocation().getX() != null){
                        preparedUpdateMovieDirectorLocationXByIdStatement.setDouble(1, newMovie.getDirector().getLocation().getX());
                        preparedUpdateMovieDirectorLocationXByIdStatement.setLong(2, movieId);
                        if(preparedUpdateMovieDirectorLocationXByIdStatement.executeUpdate() == 0) throw new SQLException();
                    }
                    preparedUpdateMovieDirectorLocationYByIdStatement.setDouble(1, newMovie.getDirector().getLocation().getY());
                    preparedUpdateMovieDirectorLocationYByIdStatement.setLong(2, movieId);
                    if(preparedUpdateMovieDirectorLocationYByIdStatement.executeUpdate() == 0) throw new SQLException();
                    if(newMovie.getDirector().getLocation().getZ() != null){
                        preparedUpdateMovieDirectorLocationZByIdStatement.setDouble(1, newMovie.getDirector().getLocation().getZ());
                        preparedUpdateMovieDirectorLocationZByIdStatement.setLong(2, movieId);
                        if(preparedUpdateMovieDirectorLocationZByIdStatement.executeUpdate() == 0) throw new SQLException();
                    }
                    if(newMovie.getDirector().getLocation().getName() != null){
                        preparedUpdateMovieDirectorLocationNameByIdStatement.setString(1, newMovie.getDirector().getLocation().getName());
                        preparedUpdateMovieDirectorLocationNameByIdStatement.setLong(2, movieId);
                        if(preparedUpdateMovieDirectorLocationNameByIdStatement.executeUpdate() == 0) throw new SQLException();
                    }
                }

            }
            databaseHandler.commit();
        } catch (SQLException exception) {
            databaseHandler.rollback();
            throw new DatabaseHandlingException();
        } finally {
            databaseHandler.closePreparedStatement(preparedUpdateMovieNameByIdStatement);
            databaseHandler.closePreparedStatement(preparedUpdateMovieCreationDateByIdStatement);
            databaseHandler.closePreparedStatement(preparedUpdateMovieOscarsCountByIdStatement);
            databaseHandler.closePreparedStatement(preparedUpdateMovieGenreByIdStatement);
            databaseHandler.closePreparedStatement(preparedUpdateMovieMpaaRatingByIdStatement);
            databaseHandler.closePreparedStatement(preparedUpdateMovieCoordinatesXByIdStatement);
            databaseHandler.closePreparedStatement(preparedUpdateMovieCoordinatesYByIdStatement);
            databaseHandler.closePreparedStatement(preparedUpdateMovieDirectorNameByIdStatement);
            databaseHandler.closePreparedStatement(preparedUpdateMovieDirectorHeightByIdStatement);
            databaseHandler.closePreparedStatement(preparedUpdateMovieDirectorEyeColorByIdStatement);
            databaseHandler.closePreparedStatement(preparedUpdateMovieDirectorHairColorByIdStatement);
            databaseHandler.closePreparedStatement(preparedUpdateMovieDirectorNationalityByIdStatement);
            databaseHandler.closePreparedStatement(preparedUpdateMovieDirectorLocationXByIdStatement);
            databaseHandler.closePreparedStatement(preparedUpdateMovieDirectorLocationYByIdStatement);
            databaseHandler.closePreparedStatement(preparedUpdateMovieDirectorLocationZByIdStatement);
            databaseHandler.closePreparedStatement(preparedUpdateMovieDirectorLocationNameByIdStatement);
            databaseHandler.setNormalMode();
        }
    }

    /**
     * Delete Marine by id.
     *
     * @param movieId Id of Movie.
     * @throws DatabaseHandlingException When there's exception inside.
     */
    public void deleteMovieById(long movieId) throws DatabaseHandlingException {
        PreparedStatement preparedDeleteMovieByIdStatement = null;
        try {
            preparedDeleteMovieByIdStatement = databaseHandler.getPreparedStatement(DELETE_MOVIE_BY_ID, false);
            preparedDeleteMovieByIdStatement.setLong(1, movieId);
            if (preparedDeleteMovieByIdStatement.executeUpdate() == 0) throw new DatabaseHandlingException();
        } catch (SQLException exception) {
            throw new DatabaseHandlingException();
        } finally {
            databaseHandler.closePreparedStatement(preparedDeleteMovieByIdStatement);
        }
    }

    /**
     * Checks Marine user id.
     *
     * @param movieId Id of Marine.
     * @param user     Owner of marine.
     * @return Is everything ok.
     * @throws DatabaseHandlingException When there's exception inside.
     */
    public boolean checkMovieUserId(long movieId, User user) throws DatabaseHandlingException {
        PreparedStatement preparedSelectMovieByIdAndUserIdStatement = null;
        try {
            preparedSelectMovieByIdAndUserIdStatement = databaseHandler.getPreparedStatement(SELECT_MOVIE_BY_ID_AND_USER_ID, false);
            preparedSelectMovieByIdAndUserIdStatement.setLong(1, movieId);
            preparedSelectMovieByIdAndUserIdStatement.setLong(2, databaseUserManager.getUserIdByUsername(user));
            ResultSet resultSet = preparedSelectMovieByIdAndUserIdStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException exception) {
            throw new DatabaseHandlingException();
        } finally {
            databaseHandler.closePreparedStatement(preparedSelectMovieByIdAndUserIdStatement);
        }
    }

    /**
     * Clear the collection.
     *
     * @throws DatabaseHandlingException When there's exception inside.
     */
    public void clearCollection() throws DatabaseHandlingException {
        PriorityBlockingQueue<Movie> moviesCollection = getCollection();
        for (Movie movie : moviesCollection) {
            deleteMovieById(movie.getId());
        }
    }
}
