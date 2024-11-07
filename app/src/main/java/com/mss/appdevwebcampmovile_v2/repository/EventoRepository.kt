package com.mss.appdevwebcampmovile_v2.repository

import com.mss.appdevwebcampmovile_v2.api.RetrofitClient
import com.mss.appdevwebcampmovile_v2.models.Dashboard
import com.mss.appdevwebcampmovile_v2.models.Evento
import retrofit2.Response

class EventoRepository {
    suspend fun getEventos(): Map<String, List<Evento>> {
        return RetrofitClient.eventoApi.listarEventos()
    }
}