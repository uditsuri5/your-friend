package com.example.homescreen

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "UserResponses.db"
        private const val DATABASE_VERSION = 4  // Updated to version 4 to reflect new schema
        const val TABLE_NAME = "responses"
        const val COLUMN_ID = "id"
        const val COLUMN_USER_EMAIL = "user_email"
        const val COLUMN_QUESTION = "question"
        const val COLUMN_RESPONSE = "response"
        const val COLUMN_TEST_SCORE = "test_score"
        const val COLUMN_TEST_NUMBER = "test_number"  // New column for test number
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, 
                $COLUMN_USER_EMAIL TEXT, 
                $COLUMN_QUESTION TEXT, 
                $COLUMN_RESPONSE TEXT, 
                $COLUMN_TEST_SCORE INTEGER,
                $COLUMN_TEST_NUMBER INTEGER
            )
        """
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 4) {
            // Check if the column exists before adding it
            try {
                var cursor = db.rawQuery("PRAGMA table_info($TABLE_NAME);", null)
                var columnExists = false
                while (cursor.moveToNext()) {
                    val columnName = cursor.getString(cursor.getColumnIndex("name"))
                    if (columnName == COLUMN_USER_EMAIL) {
                        columnExists = true
                        break
                    }
                }
                cursor.close()

                // Add the column only if it doesn't exist
                if (!columnExists) {
                    db.execSQL("ALTER TABLE $TABLE_NAME ADD COLUMN $COLUMN_USER_EMAIL TEXT")
                }

                columnExists = false
                cursor = db.rawQuery("PRAGMA table_info($TABLE_NAME);", null) // Reassign cursor
                while (cursor.moveToNext()) {
                    val columnName = cursor.getString(cursor.getColumnIndex("name"))
                    if (columnName == COLUMN_TEST_SCORE) {
                        columnExists = true
                        break
                    }
                }
                cursor.close()

                // Add the column only if it doesn't exist
                if (!columnExists) {
                    db.execSQL("ALTER TABLE $TABLE_NAME ADD COLUMN $COLUMN_TEST_SCORE INTEGER")
                }

                columnExists = false
                cursor = db.rawQuery("PRAGMA table_info($TABLE_NAME);", null) // Reassign cursor again
                while (cursor.moveToNext()) {
                    val columnName = cursor.getString(cursor.getColumnIndex("name"))
                    if (columnName == COLUMN_TEST_NUMBER) {
                        columnExists = true
                        break
                    }
                }
                cursor.close()

                // Add the column only if it doesn't exist
                if (!columnExists) {
                    db.execSQL("ALTER TABLE $TABLE_NAME ADD COLUMN $COLUMN_TEST_NUMBER INTEGER")
                }

            } catch (e: Exception) {
                e.printStackTrace()  // Handle the exception or log it
            }
        }
    }


    // Insert a new test score for a user with a specific test number
    fun insertTestScore(email: String, score: Int, testNumber: Int): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_USER_EMAIL, email)
            put(COLUMN_TEST_SCORE, score)
            put(COLUMN_TEST_NUMBER, testNumber)
        }
        return db.insert(TABLE_NAME, null, values)
    }

    // Retrieve all test scores for a user, ordered by test number
    fun getTestScoresForUser(email: String): List<Pair<Int, Int>> {
        val db = readableDatabase
        val cursor = db.query(
            TABLE_NAME,
            arrayOf(COLUMN_TEST_NUMBER, COLUMN_TEST_SCORE),
            "$COLUMN_USER_EMAIL = ?",
            arrayOf(email),
            null,
            null,
            "$COLUMN_TEST_NUMBER ASC"
        )
        val scores = mutableListOf<Pair<Int, Int>>()
        if (cursor.moveToFirst()) {
            do {
                val testNumber = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_TEST_NUMBER))
                val score = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_TEST_SCORE))
                scores.add(Pair(testNumber, score))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return scores
    }

    // Retrieve the latest test score for a user (based on the latest test number)
    fun getLatestTestScore(email: String): Int {
        val db = readableDatabase
        val cursor = db.query(
            TABLE_NAME,
            arrayOf(COLUMN_TEST_NUMBER, COLUMN_TEST_SCORE),
            "$COLUMN_USER_EMAIL = ?",
            arrayOf(email),
            null,
            null,
            "$COLUMN_TEST_NUMBER DESC",  // Sort by test number in descending order
            "1"  // Limit to 1 to get the latest test score
        )

        var latestScore = 0
        if (cursor.moveToFirst()) {
            latestScore = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_TEST_SCORE))
        }
        cursor.close()
        return latestScore
    }

    // Update the latest test score for a user with a specific test number
    fun updateLatestTestScore(email: String, score: Int, testNumber: Int) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_USER_EMAIL, email)
            put(COLUMN_TEST_SCORE, score)
            put(COLUMN_TEST_NUMBER, testNumber)
        }

        // First, delete the existing test score for the specific test number
        db.delete(TABLE_NAME, "$COLUMN_USER_EMAIL = ? AND $COLUMN_TEST_NUMBER = ?", arrayOf(email, testNumber.toString()))

        // Insert the updated test score
        db.insert(TABLE_NAME, null, values)
    }

    // Insert responses for a specific user
    fun insertResponse(email: String, question: String, response: String): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_USER_EMAIL, email)
            put(COLUMN_QUESTION, question)
            put(COLUMN_RESPONSE, response)
        }
        return db.insert(TABLE_NAME, null, values)
    }

    // Retrieve all responses for a specific user
    fun getUserResponses(email: String): List<Pair<String, String>> {
        val db = readableDatabase
        val cursor = db.query(
            TABLE_NAME,
            arrayOf(COLUMN_QUESTION, COLUMN_RESPONSE),
            "$COLUMN_USER_EMAIL = ?",
            arrayOf(email),
            null,
            null,
            null
        )

        val responses = mutableListOf<Pair<String, String>>()
        if (cursor.moveToFirst()) {
            do {
                val question = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_QUESTION))
                val response = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RESPONSE))
                responses.add(Pair(question, response))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return responses
    }
}
