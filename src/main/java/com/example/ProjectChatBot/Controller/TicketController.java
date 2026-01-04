package com.example.ProjectChatBot.Controller;

import com.example.ProjectChatBot.DTO.*;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.ProjectChatBot.Entity.Project;
import com.example.ProjectChatBot.Entity.ProjectModule;
import com.example.ProjectChatBot.Entity.Ticket;
import com.example.ProjectChatBot.Entity.User;
import com.example.ProjectChatBot.Repository.ModuleRepository;
import com.example.ProjectChatBot.Repository.ProjectRepository;
import com.example.ProjectChatBot.Repository.TicketRepository;
import com.example.ProjectChatBot.Repository.UserRepository;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ModuleRepository moduleRepository;
    
    
    private TicketResponseDTO convertToDTO(Ticket t) {

        TicketResponseDTO dto = new TicketResponseDTO();

        dto.setId(t.getId());
        dto.setTitle(t.getTitle());
        dto.setDescription(t.getDescription());
        dto.setDays(t.getDays());
        dto.setStatus(t.getStatus());
        dto.setTicketCode(t.getTicketCode());

        // USER
        SimpleUserDTO u = new SimpleUserDTO();
        u.setId(t.getUser().getId());
        u.setName(t.getUser().getName());
        u.setEmail(t.getUser().getEmail());
        u.setRole(t.getUser().getRole());
        dto.setUser(u);

        // PROJECT
        SimpleProjectDTO p = new SimpleProjectDTO();
        p.setId(t.getProject().getId());
        p.setName(t.getProject().getName());
        p.setUrl(t.getProject().getUrl());
        p.setDomain(t.getProject().getDomain());
        dto.setProject(p);

        // MODULE
        SimpleModuleDTO m = new SimpleModuleDTO();
        m.setId(t.getModule().getId());
        m.setModuleName(t.getModule().getModuleName());
        dto.setModule(m);

        return dto;
    }
    

    @PostMapping
    public TicketResponseDTO create(@RequestBody Ticket req) {

        Ticket t = new Ticket();

        t.setTitle(req.getTitle());
        t.setDescription(req.getDescription());
        t.setDays(req.getDays());
        t.setStatus("OPEN");
        t.setTicketCode(generateTicketCode());

        // GET USER
        User u = userRepository.findById(req.getUser().getId()).orElseThrow();

        // GET PROJECT
        Project p = projectRepository.findById(req.getProject().getId()).orElseThrow();

        // GET MODULE
        ProjectModule m = moduleRepository.findById(req.getModule().getId()).orElseThrow();

        t.setUser(u);
        t.setProject(p);
        t.setModule(m);

        Ticket saved = ticketRepository.save(t);
        return convertToDTO(saved);
    }


    @GetMapping
    public List<TicketResponseDTO> list() {
        return ticketRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }


    // GET TICKET BY ID
    @GetMapping("/{id}")
    public TicketResponseDTO getById(@PathVariable Long id) {
        Ticket t = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        return convertToDTO(t);
    }


    // GET TICKET BY CODE
    @GetMapping("/code/{code}")
    public TicketResponseDTO getByCode(@PathVariable String code) {
        Ticket t = ticketRepository.findByTicketCode(code).orElseThrow();
        return convertToDTO(t);
    }
    
    // DELETE TICKET
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        ticketRepository.deleteById(id);
    }


	/*
	 * // FILTER BY USER
	 * 
	 * @GetMapping("/user/{userId}") public List<Ticket> getByUser(@PathVariable
	 * Long userId) { User u = userRepository.findById(userId).orElseThrow(); return
	 * ticketRepository.findByUser(u); }
	 * 
	 * 
	 * // FILTER BY PROJECT
	 * 
	 * @GetMapping("/project/{projectId}") public List<Ticket>
	 * getByProject(@PathVariable Long projectId) { Project p =
	 * projectRepository.findById(projectId).orElseThrow(); return
	 * ticketRepository.findByProject(p); }
	 * 
	 * 
	 * // FILTER BY MODULE
	 * 
	 * @GetMapping("/module/{moduleId}") public List<Ticket>
	 * getByModule(@PathVariable Long moduleId) { ProjectModule m =
	 * moduleRepository.findById(moduleId).orElseThrow(); return
	 * ticketRepository.findByModule(m); }
	 * 
	 */
    
    @PutMapping("/{id}/status")
    public TicketResponseDTO updateStatus(@PathVariable Long id,
                                          @RequestParam String status) {

        Ticket t = ticketRepository.findById(id).orElseThrow();
        t.setStatus(status.toUpperCase());

        return convertToDTO(ticketRepository.save(t));
    }


    private String generateTicketCode() {
        return "TKT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

}
