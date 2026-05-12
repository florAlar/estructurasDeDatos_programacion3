package service;

import data.loader.CamionesLoader;
import repo.CamionRepository;

//consultar si conviene tener esta clase o no, por separacion de responsabilidad.

public class CamionService {

    CamionRepository camionRepo;

    public CamionService(String pathCamiones) {
        CamionesLoader camionesLoader = new CamionesLoader(pathCamiones);
        this.camionRepo = camionesLoader.almacenarEnRepo();
    }

    // CamionService
    //-   buscar disponibles
    //-  filtrar refrigerados
    //-  calcular capacidad
    //- preguntar si puede cargar
    // metodos utiles para el backtrackign y greedy
}