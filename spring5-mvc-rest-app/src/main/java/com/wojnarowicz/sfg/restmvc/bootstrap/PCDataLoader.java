package com.wojnarowicz.sfg.restmvc.bootstrap;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.wojnarowicz.sfg.gw.domain.Agent;
import com.wojnarowicz.sfg.gw.repository.AgentRepository;

@Component
public class PCDataLoader implements CommandLineRunner {

    private final AgentRepository agentRepository;
    
    @Autowired
    public PCDataLoader(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadPCAgents();
    }

    private void loadPCAgents() {
        List<Agent> agents = new ArrayList<Agent>();
        
        agents.add(new Agent(111111L, 111111L, 16208330L, "Иван", "Олегович", "Примеров", null));
        agents.add(new Agent(9999999L, 9999999L, 16208330L, "Алексей", "Вячеславовна", "Тестов", null));
        agents.add(new Agent(1288176L, 1288176L, 16208330L, "Александра", "Викторовна", "Левина", null));
        agents.add(new Agent(2372404L, 2372404L, 16208330L, "Ксения", "Львовна", "Лошкарева", null));
        agents.add(new Agent(9582367L, 9582367L, 16208330L, "Николай", "Константинович", "Суров", null));
        agents.add(new Agent(9487480L, 9487480L, 16208330L, "Александр", "Андреевич", "Половинкин", null));
        agents.add(new Agent(9507570L, 9507570L, 16208330L, "Павел", "Александрович", "Гук", null));
        agents.add(new Agent(1567133L, 1567133L, 16207320L, "Мария", "Андреевна", "Горбова", null));
        agents.add(new Agent(1291852L, 1291852L, 16208330L, "Инна", "Петровна", "Пряникова", null));
        agents.add(new Agent(149L, 149L, 16208190L, "Ирина", "Михайловна", "Шилова", null));
        agents.add(new Agent(1567410L, 1567410L, 16208190L, "Любовь", "Сергеевна", "Рощина", null));
        agents.add(new Agent(2360717L, 2360717L, 16208190L, "Светлана", "Ивановна", "Бирюкова", null));
        agents.add(new Agent(1900950L, 1900950L, 15007820L, "Ольга", "Федоровна", "Аниськова", "olga_aniskova@filial.stolica.rgs.ru"));
        agents.add(new Agent(9491052L, 9491052L, 16207320L, "Наталья", "Владимировна", "Анисимова", null));
        agents.add(new Agent(1588426L, 1588426L, 16207320L, "Светлана", "Владимировна", "Новикова", "svetlana_novikova@ryazan.rgs.ru"));
        agents.add(new Agent(960029L, 960029L, 36117020L, "Яна", "Николаевна", "Евлантьева", "rgs330-mop1@rostov.rgs.ru"));
        agents.add(new Agent(994739L, 994739L, 16207320L, "Наталия", "Георгиевна", "Журавлева", "natalya_juravleva@ryazan.rgs.ru"));
        agents.add(new Agent(1296600L, 1296600L, 16207320L, "Наталия", "Георгиевна", "Журавлева", "natalya_juravleva@ryazan.rgs.ru"));
        agents.add(new Agent(9504776L, 9504776L, 16208190L, "Карина", "Германовна", "Полищук", "karina_polishuk@ryazan.rgs.ru"));
        agents.add(new Agent(2033273L, 2033273L, 16207320L, "Екатерина", "Михайловна", "Скоробогатова", "mop_agn_2@ryazan.rgs.ru"));
        agents.add(new Agent(2246933L, 2246933L, 16207320L, "Виорика", "Ивановна", "Касьянова", "viorika_kasyanova@ryazan.rgs.ru"));
        agents.add(new Agent(1632195L, 1632195L, 16207320L, "Виорика", "Ивановна", "Касьянова", "viorika_kasyanova@ryazan.rgs.ru"));
        agents.add(new Agent(1454271L, 1454271L, 16207320L, "Наталья", "Николаевна", "Суснина", "natalia_sysnina@ryazan.rgs.ru"));
        agents.add(new Agent(1759069L, 1759069L, 16208190L, "Светлана", "Алексеевна", "Хохлова", "svetlana_hohlova@ryazan.rgs.ru"));
        agents.add(new Agent(1991573L, 1991573L, 16208190L, "Светлана", "Алексеевна", "Хохлова", "svetlana_hohlova@ryazan.rgs.ru"));
        agents.add(new Agent(9489412L, 9489412L, 16208190L, "Ирина", "Александровна", "Фомина", null));
        agents.add(new Agent(1993647L, 1993647L, 16207320L, "Ирина", "Александровна", "Суркова", "mop_agn_1@ryazan.rgs.ru"));
        agents.add(new Agent(2361027L, 2361027L, 16207320L, "Павел", "Павлович", "Торпищин", null));
        agents.add(new Agent(2187168L, 2187168L, 16207320L, "Елена", "Николаевна", "Пеленева", "mop_bank@ryazan.rgs.ru"));
        agents.add(new Agent(9501409L, 9501409L, 16208330L, "Оксана", "Вячеславовна", "Сударова", null));
        agents.add(new Agent(1599803L, 1599803L, 14360010L, "Сергей", "Анатольевич", "Максимов", "sergey_maximov@foc.rgs.ru"));
        agents.add(new Agent(9508381L, 9508381L, 19950000L, "Мария", "Александровна", "Юшкова", "Mariya_Yushkova@rgs.ru"));
        
        agentRepository.saveAll(agents);
    }
}
