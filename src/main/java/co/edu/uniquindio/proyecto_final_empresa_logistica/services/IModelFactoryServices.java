package co.edu.uniquindio.proyecto_final_empresa_logistica.services;

import co.edu.uniquindio.proyecto_final_empresa_logistica.decorator.IEnvio;
import co.edu.uniquindio.proyecto_final_empresa_logistica.strategy.ICriteriosStrategy;

public interface IModelFactoryServices extends IAdministradorServices, IRepartidorServices, IUsuarioServices, ICriteriosStrategy, IEnvio {
}
