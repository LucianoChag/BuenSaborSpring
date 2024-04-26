package buenSaborSpring.demo;


import buenSaborSpring.demo.repositories.*;
import buenSaborSpring.demo.domain.entities.*;
import buenSaborSpring.demo.domain.entities.enums.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalTime;


@SpringBootApplication
public class BuenSaborSpringApplication {

	//Inicializamos todos los repositorios
	private static final Logger logger = LoggerFactory.getLogger(BuenSaborSpringApplication.class);

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PaisRepository paisRepository;

	@Autowired
	private ProvinciaRepository provinciaRepository;

	@Autowired
	private LocalidadRepository localidadRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private SucursalRepository	sucursalRepository;

	@Autowired
	private DomicilioRepository domicilioRepository;

	@Autowired
	private UnidadMedidaRepository unidadMedidaRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ArticuloInsumoRepository articuloInsumoRepository;

	@Autowired
	private ArticuloManufacturadoRepository articuloManufacturadoRepository;

	@Autowired
	private ImagenRepository imagenRepository;

	@Autowired
	private PromocionRepository promocionRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;
	public static void main(String[] args) {
		SpringApplication.run(BuenSaborSpringApplication.class, args);
		System.out.println("Funciona");
	}

	@Bean
	CommandLineRunner init() {
		return args -> {
			logger.info("----------------ESTOY----FUNCIONANDO---------------------");
			// Etapa del dashboard
			// Crear 1 pais
			// Crear 2 provincias para ese pais
			// crear 2 localidades para cada provincia
			Pais pais1 = Pais.builder().nombre("Argentina").build();
			Provincia prov1 = Provincia.builder().nombre("Santa Fe").pais(pais1).build();
			Provincia prov2 = Provincia.builder().nombre("Mendoza").pais(pais1).build();
			Localidad loc1 = Localidad.builder().nombre("Rosario").provincia(prov1).build();
			Localidad loc2 = Localidad.builder().nombre("Godoy Cruz").provincia(prov2).build();


			paisRepository.save(pais1);
			provinciaRepository.save(prov1);
			provinciaRepository.save(prov2);
			localidadRepository.save(loc1);
			localidadRepository.save(loc2);


			// Crear 1 empresa
			// Crear 2 sucursales para esa empresa
			// crear los Domicilios para esas sucursales
			Empresa empresaJX = Empresa.builder().nombre("Juan X y sus amigos").cuit(93949596).razonSocial("Venta de Comida Rapida").build();
			Sucursal sucursalRosario = Sucursal.builder().nombre("En Rosario").horarioApertura(LocalTime.of(17,0)).horarioCierre(LocalTime.of(23,0)).build();
			Sucursal sucursalDorrego = Sucursal.builder().nombre("En Dorrego").horarioApertura(LocalTime.of(16,0)).horarioCierre(LocalTime.of(23,30)).build();
			Domicilio domicilioRosario = Domicilio.builder().codigoPostal(4550).calle("Calle de Rosario").numero(654).piso(3).nDpto(2).localidad(loc1).build();
			Domicilio domicilioMoldes = Domicilio.builder().codigoPostal(5521).calle("Moldes").numero(789).localidad(loc2).build();

			sucursalRosario.setDomicilio(domicilioRosario);
			sucursalDorrego.setDomicilio(domicilioMoldes);
			empresaJX.getSucursales().add(sucursalRosario);
			empresaJX.getSucursales().add(sucursalDorrego);
			domicilioRepository.save(domicilioRosario);
			domicilioRepository.save(domicilioMoldes);
			sucursalRepository.save(sucursalRosario);
			sucursalRepository.save(sucursalDorrego);
			empresaRepository.save(empresaJX);

			// Crear Unidades de medida
			UnidadMedida umLitros = UnidadMedida.builder().denominacion("Litros").build();
			UnidadMedida umGramos = UnidadMedida.builder().denominacion("Gramos").build();
			UnidadMedida umCantidad = UnidadMedida.builder().denominacion("Cantidad").build();
			UnidadMedida umPorciones = UnidadMedida.builder().denominacion("Porciones").build();
			unidadMedidaRepository.save(umLitros);
			unidadMedidaRepository.save(umGramos);
			unidadMedidaRepository.save(umCantidad);
			unidadMedidaRepository.save(umPorciones);

			// Crear Categorías de productos y subCategorías de los mismos
			Categoria cHamburguesas = Categoria.builder().denominacion("Hamburguesas").build();
			categoriaRepository.save(cHamburguesas);
			Categoria cCarne = Categoria.builder().denominacion("Carne").build();
			categoriaRepository.save(cCarne);
			Categoria cPizzas = Categoria.builder().denominacion("Pizzas").build();
			categoriaRepository.save(cPizzas);
			cHamburguesas.getSubCategorias().add(cCarne);
			categoriaRepository.save(cHamburguesas);

			// Crear Insumos , coca cola , harina , etc
			ArticuloInsumo carneMolida = ArticuloInsumo.builder().denominacion("Carne Molida").unidadMedida(umLitros).esParaElaborar(false).stockActual(5).stockMaximo(50).precioCompra(300.0).precioVenta(500.00).build();
			ArticuloInsumo harina = ArticuloInsumo.builder().denominacion("Harina").unidadMedida(umGramos).esParaElaborar(true).stockActual(4).stockMaximo(40).precioCompra(400.0).precioVenta(600.5).build();
			ArticuloInsumo queso = ArticuloInsumo.builder().denominacion("Queso").unidadMedida(umGramos).esParaElaborar(true).stockActual(20).stockMaximo(50).precioCompra(230.6).precioVenta(660.6).build();
			ArticuloInsumo tomate = ArticuloInsumo.builder().denominacion("Tomate").unidadMedida(umCantidad).esParaElaborar(true).stockActual(20).stockMaximo(50).precioCompra(230.6).precioVenta(606.6).build();

			// crear fotos para cada insumo
			Imagen imagenCarneMolida = Imagen.builder().url("https://http2.mlstatic.com/D_NQ_NP_846751-MLA50878406087_072022-O.webp").build();
			Imagen imagenHarina = Imagen.builder().url("https://mandolina.co/wp-content/uploads/2023/03/648366622-1024x683.jpg").build();
			Imagen imagenQueso = Imagen.builder().url("https://superdepaso.com.ar/wp-content/uploads/2021/06/SANTAROSA-PATEGRAS-04.jpg").build();
			Imagen imagenTomate = Imagen.builder().url("https://thefoodtech.com/wp-content/uploads/2020/06/Componentes-de-calidad-en-el-tomate-828x548.jpg").build();
			imagenRepository.save(imagenCarneMolida);
			imagenRepository.save(imagenHarina);
			imagenRepository.save(imagenQueso);
			imagenRepository.save(imagenTomate);

			carneMolida.getImagenes().add(imagenCarneMolida);
			harina.getImagenes().add(imagenHarina);
			queso.getImagenes().add(imagenQueso);
			tomate.getImagenes().add(imagenTomate);
			articuloInsumoRepository.save(carneMolida);
			articuloInsumoRepository.save(harina);
			articuloInsumoRepository.save(queso);
			articuloInsumoRepository.save(tomate);

			// Crear Articulos Manufacturados
			ArticuloManufacturado pizzaMuzarella = ArticuloManufacturado.builder().denominacion("Pizza Muzarella").descripcion("Una pizza clasica").unidadMedida(umPorciones).precioVenta(130.0).tiempoEstimadoMinutos(15).preparacion("Pasos de preparacion de una muzza de toda la vida").build();
			ArticuloManufacturado pizzaNapolitana = ArticuloManufacturado.builder().denominacion("Pizza Muzarella").descripcion("Una pizza clasica").unidadMedida(umPorciones).precioVenta(150.0).tiempoEstimadoMinutos(15).preparacion("Pasos de preparacion de una pizza napolitana italiana").build();

			// Crear fotos para los artículos manufacturados
			Imagen imagenPizzaMuzarella = Imagen.builder().url("https://storage.googleapis.com/fitia-api-bucket/media/images/recipe_images/1002846.jpg").build();
			Imagen imagenPizzaNapolitana = Imagen.builder().url("https://assets.elgourmet.com/wp-content/uploads/2023/03/8metlvp345_portada-pizza-1024x686.jpg.webp").build();
			imagenRepository.save(imagenPizzaMuzarella);
			imagenRepository.save(imagenPizzaNapolitana);

			pizzaMuzarella.getImagenes().add(imagenPizzaMuzarella);
			pizzaNapolitana.getImagenes().add(imagenPizzaNapolitana);
			articuloManufacturadoRepository.save(pizzaMuzarella);
			articuloManufacturadoRepository.save(pizzaNapolitana);

			// Establecer las relaciones entre estos objetos.
			ArticuloManufacturadoDetalle detalle1 = ArticuloManufacturadoDetalle.builder().articuloInsumo(harina).cantidad(300).build();
			ArticuloManufacturadoDetalle detalle2 = ArticuloManufacturadoDetalle.builder().articuloInsumo(queso).cantidad(600).build();
			ArticuloManufacturadoDetalle detalle3 = ArticuloManufacturadoDetalle.builder().articuloInsumo(harina).cantidad(350).build();
			ArticuloManufacturadoDetalle detalle4 = ArticuloManufacturadoDetalle.builder().articuloInsumo(queso).cantidad(650).build();
			ArticuloManufacturadoDetalle detalle5 = ArticuloManufacturadoDetalle.builder().articuloInsumo(tomate).cantidad(2).build();

			pizzaMuzarella.getArticuloManufacturadoDetalles().add(detalle1);
			pizzaMuzarella.getArticuloManufacturadoDetalles().add(detalle2);
			pizzaNapolitana.getArticuloManufacturadoDetalles().add(detalle3);
			pizzaNapolitana.getArticuloManufacturadoDetalles().add(detalle4);
			pizzaNapolitana.getArticuloManufacturadoDetalles().add(detalle5);
			articuloManufacturadoRepository.save(pizzaMuzarella);
			articuloManufacturadoRepository.save(pizzaNapolitana);

			// Establecer relaciones de las categorias
			cCarne.getArticulos().add(carneMolida);
			cPizzas.getArticulos().add(pizzaMuzarella);
			cPizzas.getArticulos().add(pizzaNapolitana);
			categoriaRepository.save(cCarne);
			categoriaRepository.save(cPizzas);

			// Crear promocion para sucursal - Dia de los enamorados
			// Tener en cuenta que esa promocion es exclusivamente para una sucursal determinada d euna empresa determinada
			Promocion promocionDiaEnamorados = Promocion.builder().denominacion("Dia de los Enamorados")
					.fechaDesde(LocalDate.of(2024,2,13))
					.fechaHasta(LocalDate.of(2024,2,15))
					.horaDesde(LocalTime.of(0,0))
					.horaHasta(LocalTime.of(23,59))
					.descripcionDescuento("El descuento que se hace por san valentin, un dia antes y un dia despues")
					.precioPromocional(100.0)
					.tipoPromocion(TipoPromocion.promocion)
					.build();
			promocionDiaEnamorados.getArticulos().add(carneMolida);
			promocionDiaEnamorados.getArticulos().add(pizzaNapolitana);
			promocionRepository.save(promocionDiaEnamorados);

			//Agregar categorias y promociones a sucursales
			sucursalRosario.getCategorias().add(cHamburguesas);
			sucursalRosario.getCategorias().add(cPizzas);
			sucursalRosario.getPromociones().add(promocionDiaEnamorados);

			sucursalDorrego.getCategorias().add(cHamburguesas);
			sucursalDorrego.getCategorias().add(cPizzas);

			sucursalRepository.save(sucursalRosario);
			sucursalRepository.save(sucursalDorrego);

			//Crea un cliente y un usuario
			Imagen imagenCliente = Imagen.builder().url("https://hips.hearstapps.com/hmg-prod/images/la-la-land-final-1638446140.jpg").build();
			imagenRepository.save(imagenCliente);
			Usuario usuario = Usuario.builder().username("Juancito").auth0Id("9565a49d-ecc1-4f4e-adea-6cdcb7edc4a3").build();
			usuarioRepository.save(usuario);
			Cliente cliente = Cliente.builder().usuario(usuario)
					.imagen(imagenCliente)
					.email("juancito@gmail.com")
					.nombre("Juancito")
					.apellido("Equisde")
					.telefono("2615926525")
					.build();
			cliente.getDomicilios().add(domicilioRosario);
			clienteRepository.save(cliente);

			//Crea un pedido para el cliente
			Pedido pedido = Pedido.builder().fechaPedido(LocalDate.now())
					.horaEstimadaFinalizacion(LocalTime.now())
					.total(300.0)
					.totalCosto(170.6)
					.estado(Estado.Preparacion)
					.formaPago(FormaPago.MercadoPago)
					.tipoEnvio(TipoEnvio.Delivery)
					.sucursal(sucursalRosario)
					.domicilio(domicilioRosario).build();

			DetallePedido detallePedido1 = DetallePedido.builder().articulo(pizzaMuzarella).cantidad(1).subTotal(200.0).build();
			DetallePedido detallePedido2 = DetallePedido.builder().articulo(carneMolida).cantidad(2).subTotal(100.0).build();

			pedido.getDetallePedidos().add(detallePedido1);
			pedido.getDetallePedidos().add(detallePedido2);
			pedidoRepository.save(pedido);

			cliente.getPedidos().add(pedido);
			clienteRepository.save(cliente);

			logger.info("----------------Sucursal Rosario ---------------------");
			logger.info("{}",sucursalRosario);
			logger.info("----------------Sucursal Dorrego ---------------------");
			logger.info("{}",sucursalDorrego);
			logger.info("----------------Pedido ---------------------");
			logger.info("{}",pedido);
		};
	}

}
