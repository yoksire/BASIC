package com.example.basic

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

class SBQuery {
    companion object{

        val supabase = createSupabaseClient(
            supabaseUrl = "https://pgihnxmtvfsupprzbwzh.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBnaWhueG10dmZzdXBwcnpid3poIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTQ5NTc1NDAsImV4cCI6MjAzMDUzMzU0MH0.sYuOeJ9ta-MQePKFe2DCMTw2kXlGHVWLEqq0UjHRR9k"
        ) {
            install(Postgrest)

            //install other modules
        }
    }
}