package br.org.serratec.dto.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.org.serratec.dto.domain.Foto;
import br.org.serratec.dto.domain.Funcionario;
import br.org.serratec.dto.dto.FuncionarioDTO;
import br.org.serratec.dto.dto.FuncionarioSalarioDTO;
import br.org.serratec.dto.repository.FuncionarioRepository;
import br.org.serratec.dto.service.FotoService;
import br.org.serratec.dto.service.FuncionarioService;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private FotoService fotoService;

	@Autowired
	private FuncionarioService funcionarioService;

//	@GetMapping
//	public ResponseEntity<List<Funcionario>> listar() {
//		List<Funcionario> funcionarios = funcionarioRepository.findAll();
//		return ResponseEntity.ok(funcionarios);
//	}

	@GetMapping("/pagina")
	public ResponseEntity<Page<Funcionario>> listarPagina(
			@PageableDefault(sort = "nome", direction = Sort.Direction.ASC, page = 3, size = 8) Pageable pageable) {
		Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);
		return ResponseEntity.ok(funcionarios);
	}

	@GetMapping("/salario")
	public ResponseEntity<Page<Funcionario>> listarSalarios(@RequestParam(defaultValue = "0") Double valorMinimo,
			@RequestParam(defaultValue = "20000") Double valorMaximo, Pageable pageable) {
		Page<Funcionario> funcionarios = funcionarioRepository.findBySalarioBetween(valorMinimo, valorMaximo, pageable);
		return ResponseEntity.ok(funcionarios);
	}

	@GetMapping("/nome")
	public ResponseEntity<Page<Funcionario>> buscarPorNome(@RequestParam(defaultValue = "") String paramNome,
			Pageable pageable) {
		Page<Funcionario> funcionarios = funcionarioRepository.findByNomeContainingIgnoreCase(paramNome, pageable);
		return ResponseEntity.ok(funcionarios);
	}

	@GetMapping("/salarios-por-idade")
	public ResponseEntity<List<FuncionarioSalarioDTO>> buscaSalariosPorIdade() {
		return ResponseEntity.ok(funcionarioRepository.buscaSalariosPorIdade());
	}

// ================================================================================

	@GetMapping("/{id}")
	public FuncionarioDTO buscar(@PathVariable Long id) {
		return funcionarioService.buscar(id);
	}

	@GetMapping
	public List<FuncionarioDTO> listar() {
		return funcionarioService.listar();
	}

	@GetMapping("/{id}/foto")
	public ResponseEntity<byte[]> buscarFoto(@PathVariable Long id) {
		Foto foto = fotoService.buscarPorIdFuncionario(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", foto.getTipo());
		headers.add("Content-length", String.valueOf(foto.getDados().length));
//		headers.add("Content-Dispositio","attachmente; filename=\"filename.jpg\"");
		return new ResponseEntity<>(foto.getDados(), headers, HttpStatus.OK);
	}

	@PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public FuncionarioDTO inserir(@RequestPart MultipartFile file, @RequestPart Funcionario funcionario)
			throws IOException {
		return funcionarioService.inserir(funcionario, file);
	}

}
