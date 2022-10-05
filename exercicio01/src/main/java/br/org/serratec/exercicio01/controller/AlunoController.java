package br.org.serratec.exercicio01.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.exercicio01.domain.Aluno;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
	private static List<Aluno> alunos = new ArrayList<Aluno>();
	static {
		alunos.add(new Aluno(1L, "Carla", "2224-0439"));
		alunos.add(new Aluno(2L, "Carlos", "2334-0239"));
		alunos.add(new Aluno(3L, "Maria", "2343-2345"));
		alunos.add(new Aluno(3L, "Marcelo", "5456-3454"));
	}

	@GetMapping
	public List<Aluno> listaAlunos() {
		return alunos;
	}

	// GET
	@GetMapping("/{matricula}")
	public Aluno buscar(@PathVariable Long matricula) {
		for (int i = 0; i < alunos.size(); i++) {
			if (alunos.get(i).getMatricula().equals(matricula)) {
				return alunos.get(i);
			}
		}
		return null;
	}

	// FAZER UM POST
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Aluno inserir(@RequestBody Aluno aluno) {
		alunos.add(aluno);
		return aluno;
	}

	// DELETAR
	@DeleteMapping("/{matricula}")
	public void delete(@PathVariable Long matricula) {
		for (int i = 0; i < alunos.size(); i++) {
			if (alunos.get(i).getMatricula().equals(matricula)) {
				alunos.remove(i);
				break;
			}
		}
	}

	// ATUALIZACAO
	@PutMapping("/{matricula}")
	public Aluno atualizar(@RequestBody Aluno aluno, @PathVariable Long matricula) {
		for (int i = 0; i < alunos.size(); i++) {
			if (alunos.get(i).getMatricula().equals(matricula)) {
				Aluno alunoLista = new Aluno(matricula, aluno.getNome(), aluno.getTelefone());
				alunos.set(i, alunoLista);
				return alunoLista;
			}
		}
		return null;
	}
}
//	return lista.stream().filter(a -> a.getMatricula().equals(matricula)).findFirst().orElse(null);
