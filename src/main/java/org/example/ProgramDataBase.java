package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;

public class ProgramDataBase {
    public List<String> list;
    Map<String,List<Program>> data = new HashMap<>();
    public void importFromFile(String path) throws IOException {
        list = Files.readAllLines(new File(path).toPath(), Charset.defaultCharset());
        ArrayList<Program> listProgram = new ArrayList<>();
        String channel = null;
        String[] parts = null;
        BroadcastsTime time = null;
        String prog = null;
        for (String line : list) {
            line = line.trim();
            if (line.startsWith("#")) {
                if (prog != null){
                    data.put(channel,listProgram);
                }
                channel = line.substring(1).trim();
                parts = null;
                time = null;
                prog = null;
            } else {
                if (channel != null && time == null && line.contains(":")) {
                    parts = line.split(":");
                    String hour = parts[0].trim();
                    String minute = parts[1].trim();
                    time = new BroadcastsTime(Byte.parseByte(parts[0]), Byte.parseByte(parts[1]));
                    continue;
                }
                if (channel != null && time != null) {
                    prog = line.substring(0).trim();
                }
                Program program = new Program(channel, time, prog);
                listProgram.add(program);
            }
        }

        for (List<Program> programs : data.values()) {
            Collections.sort(programs, Comparator.comparing(Program::getTime));
        }

    }
    public List<Program> getAllPrograms() {
        List<Program> allPrograms = new ArrayList<>();
        for (List<Program> programs : data.values()) {
            allPrograms.addAll(programs);
        }
        return allPrograms;
    }

    public List<Program> getProgramsByChannel(String channel) {
        return data.getOrDefault(channel, new ArrayList<>());
    }

    public List<Program> getProgramsAt(BroadcastsTime time) {
        List<Program> programsAtTime = new ArrayList<>();
        for (List<Program> programs : data.values()) {
            for (Program program : programs) {
                if (program.getTime().equals(time)) {
                    programsAtTime.add(program);
                }
            }
        }
        return programsAtTime;
    }

    public List<Program> getProgramsByName(String name) {
        List<Program> matchingPrograms = new ArrayList<>();
        for (List<Program> programs : data.values()) {
            for (Program program : programs) {
                if (program.getName().toLowerCase().contains(name.toLowerCase())) {
                    matchingPrograms.add(program);
                }
            }
        }
        return matchingPrograms;
    }
}
