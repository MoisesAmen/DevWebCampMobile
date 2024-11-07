package com.mss.appdevwebcampmovile_v2.api

import retrofit2.http.GET
import com.mss.appdevwebcampmovile_v2.models.Evento

/*interface EventoApiService {
    @GET("api/eventos")
    suspend fun listarEventos(): List<Evento>
}*/

interface EventoApiService {
    @GET("api/eventos")
    suspend fun listarEventos(): Map<String, List<Evento>> // Modificado para que retorne un Map con listas de eventos
}