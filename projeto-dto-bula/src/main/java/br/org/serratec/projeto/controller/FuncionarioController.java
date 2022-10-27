package br.org.serratec.projeto.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import br.org.serratec.projeto.domain.Foto;
import br.org.serratec.projeto.domain.Funcionario;
import br.org.serratec.projeto.dto.FuncionarioDTO;
import br.org.serratec.projeto.dto.FuncionarioSalarioDTO;
import br.org.serratec.projeto.repository.FuncionarioRepository;
import br.org.serratec.projeto.service.FotoService;
import br.org.serratec.projeto.service.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

//	@Autowired
//	FuncionarioRepository funcionarioRepository;

	@Autowired
	FuncionarioService funcionarioService;

	@Autowired
	FotoService fotoService;

//	@GetMapping
//	public ResponseEntity<Page<Funcionario>>  listar(
//			@PageableDefault(sort = "nome", direction = Direction.ASC, size = 8, page = 3)
//			Pageable pageable ){
//		Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);
//		return ResponseEntity.ok(funcionarios);
//	}

	@GetMapping
	public ResponseEntity<List<FuncionarioDTO>> listar() {
		List<FuncionarioDTO> funcionarios = funcionarioService.listar();
		return ResponseEntity.ok(funcionarios);
	}

	@GetMapping("/{id}/foto")
	public ResponseEntity<byte[]> buscarFoto(@PathVariable Long id) {
		Foto foto = fotoService.buscaPorIdFuncionario(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", foto.getTipo());
		headers.add("Content-length", String.valueOf(foto.getDados().length));
		headers.add("Content-Disposition", "attachment; filename=\"filename.jpg\"");
		headers.add("x-teste", "123");

		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(foto.getDados());
	}

	@GetMapping("/{id}")
	public FuncionarioDTO buscar(Long id) {
		return funcionarioService.buscar(id);
	}

	@PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public FuncionarioDTO inserir(@RequestPart Funcionario funcionario, @RequestPart MultipartFile file)
			throws IOException {
		return funcionarioService.inserir(funcionario, file);
	}

//	@GetMapping("/salario")
//	public ResponseEntity<Page<Funcionario>> listarSalarios(@RequestParam(defaultValue = "0") Double valorMinimo,
//			@RequestParam(defaultValue = "20000") Double valorMaximo, Pageable pageable) {
//		Page<Funcionario> funcionarios = funcionarioRepository.findBySalarioBetween(valorMinimo, valorMaximo, pageable);
//		return ResponseEntity.ok(funcionarios);
//	}
//
//	@GetMapping("/nome")
//	public ResponseEntity<Page<Funcionario>> buscarPorNome(@RequestParam(defaultValue = "") String paramNome,
//			Pageable pageable) {
//		Page<Funcionario> funcionarios = funcionarioRepository.findByNomeContainingIgnoreCase(paramNome, pageable);
//		return ResponseEntity.ok(funcionarios);
//	}
//
//	@GetMapping("/salarios-por-idade")
//	public ResponseEntity<List<FuncionarioSalarioDTO>> buscaSalariosPorIdade() {
//		return ResponseEntity.ok(funcionarioRepository.buscaSalariosPorIdade());
//	}
//
//	@GetMapping("por-letra")
//	public ResponseEntity<List<Funcionario>> buscaPorLetra(@RequestParam String letra1, String letra2) {
//		return ResponseEntity.ok(funcionarioRepository.buscarPorLetraInicial(letra1, letra2));
//	}

}
