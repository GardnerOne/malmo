//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.01.17 at 10:30:21 AM GMT 
//


package com.microsoft.Malmo.Schemas;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element ref="{http://ProjectMalmo.microsoft.com}FlatWorldGenerator"/>
 *           &lt;element ref="{http://ProjectMalmo.microsoft.com}FileWorldGenerator"/>
 *           &lt;element ref="{http://ProjectMalmo.microsoft.com}DefaultWorldGenerator"/>
 *           &lt;element ref="{http://ProjectMalmo.microsoft.com}BiomeGenerator"/>
 *         &lt;/choice>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element ref="{http://ProjectMalmo.microsoft.com}DrawingDecorator"/>
 *           &lt;element ref="{http://ProjectMalmo.microsoft.com}AnimationDecorator"/>
 *           &lt;element ref="{http://ProjectMalmo.microsoft.com}MazeDecorator"/>
 *           &lt;element ref="{http://ProjectMalmo.microsoft.com}ClassroomDecorator"/>
 *           &lt;element ref="{http://ProjectMalmo.microsoft.com}SnakeDecorator"/>
 *           &lt;element ref="{http://ProjectMalmo.microsoft.com}MovingTargetDecorator"/>
 *           &lt;element ref="{http://ProjectMalmo.microsoft.com}BuildBattleDecorator"/>
 *           &lt;element ref="{http://ProjectMalmo.microsoft.com}NavigationDecorator"/>
 *         &lt;/choice>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element ref="{http://ProjectMalmo.microsoft.com}ServerQuitFromTimeUp"/>
 *           &lt;element ref="{http://ProjectMalmo.microsoft.com}ServerQuitWhenAnyAgentFinishes"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "worldGenerator",
    "worldDecorators",
    "serverQuitProducers"
})
@XmlRootElement(name = "ServerHandlers")
public class ServerHandlers {

    @XmlElements({
        @XmlElement(name = "FlatWorldGenerator", type = FlatWorldGenerator.class),
        @XmlElement(name = "FileWorldGenerator", type = FileWorldGenerator.class),
        @XmlElement(name = "DefaultWorldGenerator", type = DefaultWorldGenerator.class),
        @XmlElement(name = "BiomeGenerator", type = BiomeGenerator.class)
    })
    protected Object worldGenerator;
    @XmlElements({
        @XmlElement(name = "DrawingDecorator", type = DrawingDecorator.class),
        @XmlElement(name = "AnimationDecorator", type = AnimationDecorator.class),
        @XmlElement(name = "MazeDecorator", type = MazeDecorator.class),
        @XmlElement(name = "ClassroomDecorator", type = ClassroomDecorator.class),
        @XmlElement(name = "SnakeDecorator", type = SnakeDecorator.class),
        @XmlElement(name = "MovingTargetDecorator", type = MovingTargetDecorator.class),
        @XmlElement(name = "BuildBattleDecorator", type = BuildBattleDecorator.class),
        @XmlElement(name = "NavigationDecorator", type = NavigationDecorator.class)
    })
    protected List<Object> worldDecorators;
    @XmlElements({
        @XmlElement(name = "ServerQuitFromTimeUp", type = ServerQuitFromTimeUp.class),
        @XmlElement(name = "ServerQuitWhenAnyAgentFinishes", type = ServerQuitWhenAnyAgentFinishes.class)
    })
    protected List<Object> serverQuitProducers;

    /**
     * Gets the value of the worldGenerator property.
     * 
     * @return
     *     possible object is
     *     {@link FlatWorldGenerator }
     *     {@link FileWorldGenerator }
     *     {@link DefaultWorldGenerator }
     *     {@link BiomeGenerator }
     *     
     */
    public Object getWorldGenerator() {
        return worldGenerator;
    }

    /**
     * Sets the value of the worldGenerator property.
     * 
     * @param value
     *     allowed object is
     *     {@link FlatWorldGenerator }
     *     {@link FileWorldGenerator }
     *     {@link DefaultWorldGenerator }
     *     {@link BiomeGenerator }
     *     
     */
    public void setWorldGenerator(Object value) {
        this.worldGenerator = value;
    }

    /**
     * Gets the value of the worldDecorators property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the worldDecorators property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWorldDecorators().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DrawingDecorator }
     * {@link AnimationDecorator }
     * {@link MazeDecorator }
     * {@link ClassroomDecorator }
     * {@link SnakeDecorator }
     * {@link MovingTargetDecorator }
     * {@link BuildBattleDecorator }
     * {@link NavigationDecorator }
     * 
     * 
     */
    public List<Object> getWorldDecorators() {
        if (worldDecorators == null) {
            worldDecorators = new ArrayList<Object>();
        }
        return this.worldDecorators;
    }

    /**
     * Gets the value of the serverQuitProducers property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serverQuitProducers property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServerQuitProducers().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServerQuitFromTimeUp }
     * {@link ServerQuitWhenAnyAgentFinishes }
     * 
     * 
     */
    public List<Object> getServerQuitProducers() {
        if (serverQuitProducers == null) {
            serverQuitProducers = new ArrayList<Object>();
        }
        return this.serverQuitProducers;
    }

}
